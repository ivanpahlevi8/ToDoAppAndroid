package com.example.todoapp.presentation.user_connection

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.value.Constants
import com.example.todoapp.domain.models.UserConnectionModel
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.UserConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel()
class UserConnectionViewModel @Inject constructor(
    private val userConnectionUseCase: UserConnectionUseCase,
    private val sharedPreferences: SharedPreferences,
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    private var _requestConnectionState by mutableStateOf<UserConnectionState>(UserConnectionState.LoadingState)

    val requestConnectionState : State<UserConnectionState> get() = derivedStateOf { _requestConnectionState }

    private var _connectedUserState by mutableStateOf<UserConnectionState>(UserConnectionState.IdleState)

    val connectedUserState : State<UserConnectionState> get() = derivedStateOf { _connectedUserState }

    private var _acceptConnectionState by mutableStateOf<UserConnectionState>(UserConnectionState.IdleState)

    val acceptConnectionState : State<UserConnectionState> get() = derivedStateOf { _acceptConnectionState }

    private var _disconnectConnectionState by mutableStateOf<UserConnectionState>(UserConnectionState.IdleState)

    val disconnectConnectionState  : State<UserConnectionState> get() = derivedStateOf { _disconnectConnectionState }

    private var _declineConnectionState by mutableStateOf<UserConnectionState>(UserConnectionState.IdleState)

    val declineConnectionState : State<UserConnectionState> get() = derivedStateOf { _declineConnectionState }

    init {
        viewModelScope.launch {
            delay(600)

            try{
                val getData = userConnectionUseCase.getRequestConnectionUseCase(
                    userRequesterId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""
                )

                // get connection user
                val userConnections : MutableList<UserConnectionModel> = mutableListOf()

                for (item in getData){
                    val getUser = authUseCase.getUserIdUseCase(
                        userId = item.connectionUserConnectionId
                    )

                    userConnections.add(
                        UserConnectionModel(
                            connectionId = item.connectionId.toString(),
                            userConnection = getUser
                        )
                    )
                }

                _requestConnectionState = UserConnectionState.DataState(
                    data = userConnections
                )
            } catch (e : Exception) {
                val errMsg = "Error Happen : ${e.message}"

                _requestConnectionState = UserConnectionState.ErrorState(
                    errMsg = errMsg
                )
            }
        }
    }

    fun onEvent(event : UserConnectionEvent) {
        when(event) {
            is UserConnectionEvent.OnGetUserConnection -> {
                _connectedUserState = UserConnectionState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val getData = userConnectionUseCase.getAllConnectionUseCase(
                            userId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""
                        )

                        val getLoginUserId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""

                        // get connection user
                        val userConnections : MutableList<UserConnectionModel> = mutableListOf()

                        for (item in getData){
                            val getUser : UserModel = if(getLoginUserId == item.connectionUserOwnerId){
                                authUseCase.getUserIdUseCase(
                                    userId = item.connectionUserConnectionId
                                )
                            } else {
                                authUseCase.getUserIdUseCase(
                                    userId = item.connectionUserOwnerId
                                )
                            }

                            userConnections.add(
                                UserConnectionModel(
                                    connectionId = item.connectionId.toString(),
                                    userConnection = getUser
                                )
                            )
                        }


                        _connectedUserState = UserConnectionState.DataState(
                            data = userConnections
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}"

                        _connectedUserState = UserConnectionState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
            is UserConnectionEvent.OnGetRequestConnection -> {
                _requestConnectionState = UserConnectionState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val getData = userConnectionUseCase.getRequestConnectionUseCase(
                            userRequesterId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""
                        )

                        // get connection user
                        val userConnections : MutableList<UserConnectionModel> = mutableListOf()

                        for (item in getData){
                            val getUser = authUseCase.getUserIdUseCase(
                                userId = item.connectionUserConnectionId
                            )

                            userConnections.add(
                                UserConnectionModel(
                                    connectionId = item.connectionId.toString(),
                                    userConnection = getUser
                                )
                            )
                        }

                        _requestConnectionState = UserConnectionState.DataState(
                            data = userConnections
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}"

                        _requestConnectionState = UserConnectionState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
            is UserConnectionEvent.OnAcceptConnection -> {
                val connectionId = event.connectionId

                _acceptConnectionState = UserConnectionState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val response = userConnectionUseCase.acceptUserConnectionUseCase(
                            connectionId = connectionId.toInt()
                        )

                        val getUser = authUseCase.getUserIdUseCase(
                            userId = response.connectionUserConnectionId
                        )

                        _acceptConnectionState = UserConnectionState.DataState(
                            data = getUser
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}"

                        _acceptConnectionState = UserConnectionState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
            is UserConnectionEvent.OnDisconnectUserConnection -> {
                val connectionId = event.connectionId

                viewModelScope.launch {
                    _disconnectConnectionState = UserConnectionState.LoadingState

                    delay(500)

                    try{
                        val getData = userConnectionUseCase.unConnectedUserUseCase(
                            connectionId = connectionId.toInt()
                        )

                        val getUser = authUseCase.getUserIdUseCase(
                            getData.connectionUserConnectionId
                        )

                        _disconnectConnectionState = UserConnectionState.DataState(
                            data = getUser
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}"

                        _disconnectConnectionState = UserConnectionState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is UserConnectionEvent.OnDeclineUserConnection -> {
                _declineConnectionState = UserConnectionState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val data = userConnectionUseCase.declineUserUseCase(
                            connectionId = event.connectionId.toInt()
                        )

                        val userModel = authUseCase.getUserIdUseCase(
                            userId = data.connectionUserConnectionId
                        )

                        _declineConnectionState = UserConnectionState.DataState(
                            data = userModel
                        )
                    } catch (e : Exception) {
                        val errMsg : String = "Error Happen : ${e.message}"

                        _declineConnectionState = UserConnectionState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
        }
    }

    fun updateAcceptConnectionState(newState : UserConnectionState) {
        _acceptConnectionState = newState
    }

    fun updateDisconnectConnectionState(newState : UserConnectionState) {
        _disconnectConnectionState = newState
    }

    fun updateDeclineConnectionState(newState : UserConnectionState) {
        _declineConnectionState = newState
    }
}