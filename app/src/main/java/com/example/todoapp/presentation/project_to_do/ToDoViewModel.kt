package com.example.todoapp.presentation.project_to_do

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.domain.usecase.project_usecase.ProjectUseCase
import com.example.todoapp.domain.usecase.todo_socket_usecase.ToDoSocketUseCase
import com.example.todoapp.domain.usecase.todo_usecase.ToDoUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val toDoSocketUseCase: ToDoSocketUseCase,
    private val toDoUseCase: ToDoUseCase,
    private val projectUseCase: ProjectUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected
    var webSocket : WebSocket? = null

    // create set state to track grabbed to do card
    private val _grabbedToDoItem = MutableStateFlow<Set<Int>>(emptySet())
    val grabbedToDoItem : StateFlow<Set<Int>> = _grabbedToDoItem

    // create state for hold just create to do id
    private var jusCreatedToDoId by mutableStateOf(0)

    // create state for add to do item to db
    private var _createToDoItemState by mutableStateOf<ToDoState>(ToDoState.IdleState)
    val createToDoState : State<ToDoState> get() = derivedStateOf { _createToDoItemState }

    // create state for update to do item to db
    private var _updateToDoItemState by mutableStateOf<ToDoState>(ToDoState.IdleState)
    val updateToDoItemState : State<ToDoState> get() = derivedStateOf { _updateToDoItemState }

    // create list for each state of to do for real time socket
    private var _createdToDo = MutableStateFlow<List<ToDoPointerDto>>(listOf())
    val createdToDo : StateFlow<List<ToDoPointerDto>> = _createdToDo

    private var _processedToDo = MutableStateFlow<List<ToDoPointerDto>>(listOf())
    val processedToDo : StateFlow<List<ToDoPointerDto>> = _processedToDo

    private var _finishedToDo = MutableStateFlow<List<ToDoPointerDto>>(emptyList())
    val finishedToDo : StateFlow<List<ToDoPointerDto>> = _finishedToDo

    // create project detail state
    private var _projectDetailState by mutableStateOf<ProjectDetailState>(ProjectDetailState.LoadingState)
    val projectDetailState : State<ProjectDetailState> get() = derivedStateOf { _projectDetailState }

    // get project id from route
    private val getProjectId = (savedStateHandle.get<String>("projectId") ?: "0").toInt()

    init {
        // initial connect
        viewModelScope.launch {
            webSocket = toDoSocketUseCase.connectToServerUseCase(
                projectId = getProjectId,
                listener = object : WebSocketListener(){
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        _isConnected.value = true
                        Log.d("CHECk", "Set is connected to true")
                    }

                    override fun onMessage(webSocket: WebSocket, text: String) {
                        viewModelScope.launch {
                            if(!text.contains("type")){
                                val gson = Gson()
                                val toDoPointerJson = gson.fromJson(text, ToDoPointerDto::class.java)

                                // check for status
                                when(toDoPointerJson.toDoPointerStatus){
                                    ToDoPointerState.Grabbed.name -> {
                                        // add id to list
                                        _grabbedToDoItem.update {
                                                currList -> currList + (toDoPointerJson.toDoItem.toDoId ?: 0)
                                        }
                                    }
                                    ToDoPointerState.Released.name -> {
                                        // remove from list
                                        _grabbedToDoItem.update {
                                                currList -> currList - (toDoPointerJson.toDoItem.toDoId ?: 0)
                                        }
                                    }
                                    ToDoPointerState.Dropped.name -> {
                                        // remove from list
                                        _grabbedToDoItem.update {
                                                currList -> currList - (toDoPointerJson.toDoItem.toDoId ?: 0)
                                        }

                                        // update to do card position
                                        updateToDoPosition(toDoPointer = toDoPointerJson)
                                    }
                                    ToDoPointerState.Created.name -> {
                                        // incoming socket for create new to do, add to created to do
                                        _createdToDo.update {
                                            currList -> currList + toDoPointerJson
                                        }
                                    }
                                }
                            }
                        }
                    }

                    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                        viewModelScope.launch {
                            _isConnected.value = false
                        }
                    }

                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                        _isConnected.value = false
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        _isConnected.value = false
                    }
                }
            )

            // init data for project detail
            try{
                delay(600)

                val data = projectUseCase.getProjectDetailUseCase(
                    projectId = getProjectId
                )

                _projectDetailState = ProjectDetailState.DataState(
                    projectDto = data
                )
            } catch (e : Exception) {
                val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                _projectDetailState = ProjectDetailState.ErrorState(
                    errMsg = errMsg
                )
            }
        }
    }

    fun onEvent(event : ToDoEvent){
        when(event) {
            is ToDoEvent.CreateToDo -> {
                Log.d("CHECK", "Create To Do")
                // get item
                val getToDo = event.toDoDto

                viewModelScope.launch {
                    _createToDoItemState = ToDoState.LoadingState

                    delay(600)

                    try{
                        // update to do project id
                        getToDo.toDoProjectId = getProjectId
                        getToDo.toDoItemState = ToDoStatusEnum.CREATED.label

                        // add to do to db
                        val getToDoResp = toDoUseCase.createToDoUseCase(
                            createToDoDto = getToDo
                        )

                        // create to do pointer
                        val toDoPointer = ToDoPointerDto(
                            toDoPointerStatus = ToDoPointerState.Created.name,
                            toDoItem = getToDoResp
                        )

                        // send message to socket
                        sendMessage(
                            toDoPointer = toDoPointer
                        )

                        // update on local
                        _createdToDo.update {
                                currList -> currList + toDoPointer
                        }

                        // update state into idle
                        _createToDoItemState = ToDoState.IdleState
                    } catch (e : Exception) {
                        val errMsg = "Error Hapen : ${e.message}, ${e.stackTrace}"

                        _createToDoItemState = ToDoState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is ToDoEvent.UpdateToDo -> {
                Log.d("CHECK", "Update To Do")
                // get updated object
                val getToDoPointer = event.toDoPointer

                viewModelScope.launch {
                    _updateToDoItemState = ToDoState.LoadingState

                    // add some delay
                    delay(600)
                    try{
                        // send message pointer as the item to do is being dropped
                        sendMessage(getToDoPointer)

                        // update to do on database
                        toDoUseCase.updateToDoUseCase(
                            getToDoPointer.toDoItem
                        )

                        // update state
                        _updateToDoItemState = ToDoState.IdleState
                    } catch (e : Exception) {
                        val errMsg = "Error Hapen : ${e.message}, ${e.stackTrace}"

                        _updateToDoItemState = ToDoState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is ToDoEvent.OnGrabbedItem -> {
                sendMessage(event.toDoPointer)
            }
        }
    }

    // create update to do card position for local
    fun updateToDoPosition(
        toDoPointer: ToDoPointerDto?
    ){
        if(toDoPointer != null) {
            val targetId = toDoPointer.toDoItem.toDoId

            when(toDoPointer.targetToDoState) {
                ToDoStatusEnum.CREATED.label -> {
                    _createdToDo.update { currList ->
                        currList.filterNot { it.toDoItem.toDoId == targetId }
                    }
                    _finishedToDo.update { currList ->
                        currList.filterNot { it.toDoItem.toDoId == targetId }
                    }
                    _processedToDo.update {
                            currList -> currList.filterNot { it.toDoItem.toDoId == targetId }
                    }

                    // add to new position
                    _createdToDo.update { currList ->
                        currList + toDoPointer
                    }
                }
                ToDoStatusEnum.PROCESSED.label -> {
                    _createdToDo.update { currList ->
                        currList.filterNot { it.toDoItem.toDoId == targetId }
                    }
                    _finishedToDo.update { currList ->
                        currList.filterNot { it.toDoItem.toDoId == targetId }
                    }
                    _processedToDo.update {
                            currList -> currList.filterNot { it.toDoItem.toDoId == targetId }
                    }

                    // add to new position
                    _processedToDo.update { currList ->
                        currList + toDoPointer
                    }
                }
                ToDoStatusEnum.FINISHED.label -> {
                    // remove from two other
                    _createdToDo.update { currList ->
                        currList.filterNot { it.toDoItem.toDoId == targetId }
                    }
                    _finishedToDo.update { currList ->
                        currList.filterNot { it.toDoItem.toDoId == targetId }
                    }
                    _processedToDo.update {
                            currList -> currList.filterNot { it.toDoItem.toDoId == targetId }
                    }

                    // add to new position
                    _finishedToDo.update { currList ->
                        currList + toDoPointer
                    }
                }
            }
        }
    }

    fun updateAddToDoState(newState : ToDoState) {
        _createToDoItemState = newState
    }

    fun updateUpdateToDoState(newState: ToDoState) {
        _createToDoItemState = newState
    }

    private fun sendMessage(toDoPointer : ToDoPointerDto) {
        if (_isConnected.value) {
            val gson = Gson()
            val toDoPointerJson = gson.toJson(toDoPointer)

            webSocket?.send(toDoPointerJson)
        } else {
            Log.d("CHECK", "Disconnect when sending")
        }
    }
}