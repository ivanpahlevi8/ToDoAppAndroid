package com.example.todoapp.presentation.to_do

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.domain.usecase.todo_socket_usecase.ToDoSocketUseCase
import com.example.todoapp.domain.usecase.todo_usecase.ToDoUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val toDoSocketUseCase: ToDoSocketUseCase,
    private val toDoUseCase: ToDoUseCase,
    private val savedStateHandle: SavedStateHandle,
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
    private var _createToDoItemState by mutableStateOf<ToDoCreateState>(ToDoCreateState.IdleState)
    val createToDoState : State<ToDoCreateState> get() = derivedStateOf { _createToDoItemState }

    // create list for each state of to do for real time socket
    private var _createdToDo = MutableStateFlow<List<ToDoPointerDto>>(listOf(
        ToDoPointerDto(
            toDoPointerStatus = "",
            targetToDoState = "",
            toDoItem = CreateToDoDto(
                toDoId = 1,
                toDoItemName = "testname 1",
                toDoItemDescription = "Test Descriotion"
            )
        ),
        ToDoPointerDto(
            toDoPointerStatus = "",
            targetToDoState = "",
            toDoItem = CreateToDoDto(
                toDoId = 3,
                toDoItemName = "testname 3",
                toDoItemDescription = "Test Descriotion"
            )
        )
    ))
    val createdToDo : StateFlow<List<ToDoPointerDto>> = _createdToDo

    private var _processedToDo = MutableStateFlow<List<ToDoPointerDto>>(listOf(
        ToDoPointerDto(
            toDoPointerStatus = "",
            targetToDoState = "",
            toDoItem = CreateToDoDto(
                toDoId = 2,
                toDoItemName = "testname 2",
                toDoItemDescription = "Test Descriotion"
            )
        )
    ))
    val processedToDo : StateFlow<List<ToDoPointerDto>> = _processedToDo

    private var _finishedToDo = MutableStateFlow<List<ToDoPointerDto>>(emptyList())
    val finishedToDo : StateFlow<List<ToDoPointerDto>> = _finishedToDo

    // get project id from route
    val getProjectId = (savedStateHandle.get<String>("projectId") ?: "0").toInt()

    init {
        // initial connect
        viewModelScope.launch {
            webSocket = toDoSocketUseCase.connectToServerUseCase(
                projectId = 1,
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

                                Log.d("CHECK", "Check pointer json : $toDoPointerJson")

                                // check for status
                                when(toDoPointerJson.toDoPointerStatus){
                                    ToDoPointerState.Grabbed.name -> {
                                        Log.d("CHECK", "On grabbed")
                                        Log.d("CHECK", "Grabbed remote on id : ${toDoPointerJson.toDoItem.toDoId}")
                                        // add id to list
                                        _grabbedToDoItem.update {
                                                currList -> currList + (toDoPointerJson.toDoItem.toDoId ?: 0)
                                        }
                                    }
                                    ToDoPointerState.Released.name -> {
                                        Log.d("CHECK", "On released")
                                        // remove from list
                                        _grabbedToDoItem.update {
                                                currList -> currList - (toDoPointerJson.toDoItem.toDoId ?: 0)
                                        }
                                    }
                                    ToDoPointerState.Dropped.name -> {
                                        Log.d("CHECK", "On dropped")
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
                            Log.d("CHECK", "Connection failed: ${t.message}")
                        }
                    }

                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                        _isConnected.value = false
                        Log.d("CHECK", "Server closing connection. Code: $code, Reason: $reason")
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        _isConnected.value = false
                        Log.d("CHECK", "Connection fully closed. Code: $code, Reason: $reason")
                    }
                }
            )
        }
    }

    fun onEvent(event : ToDoEvent){
        when(event) {
            is ToDoEvent.CreateToDo -> {
                // get item
                val getToDo = event.toDoDto

                viewModelScope.launch {
                    _createToDoItemState = ToDoCreateState.LoadingState

                    delay(500)

                    try{
                        // update to do project id
                        getToDo.toDoProjectId = getProjectId
                        getToDo.toDoItemState = "Test Only"

                        Log.d("CHECK", "Project id : $getProjectId")

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
                        _createToDoItemState = ToDoCreateState.IdleState
                    } catch (e : Exception) {
                        Log.e("Check", "Error", e)
                    }
                }
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
                    Log.d("CHECK", "Dropped on id : $targetId")
                    Log.d("CHECK", "Current CREATED list before update: ${_createdToDo.value.joinToString { it.toDoItem.toDoItemName ?: "Unknown" }}")
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

                    Log.d("CHECK", "Current CREATED list after update: ${_createdToDo.value.joinToString { it.toDoItem.toDoItemName ?: "Unknown" }}")
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

    fun sendMessage(toDoPointer : ToDoPointerDto) {
        if (_isConnected.value) {
            val gson = Gson()
            val toDoPointerJson = gson.toJson(toDoPointer)

            webSocket?.send(toDoPointerJson)
        } else {
            Log.d("CHECK", "Disconnect when sending")
        }
    }
}