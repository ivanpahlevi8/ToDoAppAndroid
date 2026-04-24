package com.example.todoapp.presentation.search_friend

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.value.Constants
import com.example.todoapp.data.dtos.SendConnectionRequestDto
import com.example.todoapp.domain.models.SearchFriendModel
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.UserConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SearchFriendViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userConnectionUseCase: UserConnectionUseCase,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    private var _searchFriendState by mutableStateOf<SearchFriendState>(SearchFriendState.IdleState)

    val searchFriendState : State<SearchFriendState> get() = derivedStateOf { _searchFriendState }

    private var _addFriendState by mutableStateOf<SearchFriendState>(SearchFriendState.IdleState)

    val addFriendState : State<SearchFriendState> get() = derivedStateOf { _addFriendState }

    fun onEvent(event : SearchFriendEvent) {
        when(event) {
            is SearchFriendEvent.OnSearch -> {
                _searchFriendState = SearchFriendState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val userModel = authUseCase.getUserUseCase(
                            userName = event.userName
                        )

                        Log.d("CHECK", "Current user login : ${sharedPreferences.getString(Constants.USER_ID, "") ?: ""}")
                        Log.d("CHECK", "Connection user : ${userModel.userId}")

                        val isConnected = userConnectionUseCase.getIsConnectedStatusUseCase(
                            userId = sharedPreferences.getString(Constants.USER_ID, "") ?: "",
                            userConnectionId = userModel.userId
                        )

                        val searchFriend = SearchFriendModel(
                            userModel = userModel,
                            isConnected = isConnected
                        )

                        _searchFriendState = SearchFriendState.DataState(
                            data = searchFriend
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error happen : ${e.message}"

                        _searchFriendState = SearchFriendState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is SearchFriendEvent.OnAddFriend -> {
                _addFriendState = SearchFriendState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val response = userConnectionUseCase.sendUserConnectionUseCase(
                            sendConnectionRequestDto = SendConnectionRequestDto(
                                userOwnerId = sharedPreferences.getString(Constants.USER_ID, "") ?: "",
                                userConnectionId = event.userId
                            )
                        )

                        _addFriendState = SearchFriendState.DataState(
                            data = response
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error happen : ${e.message}"

                        _addFriendState = SearchFriendState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
        }
    }

    fun updateAddFriendState(newState : SearchFriendState){
        _addFriendState = newState
    }

    fun updateSearchFriendState(newState: SearchFriendState){
        _searchFriendState = newState
    }
}