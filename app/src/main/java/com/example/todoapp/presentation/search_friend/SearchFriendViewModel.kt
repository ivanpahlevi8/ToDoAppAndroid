package com.example.todoapp.presentation.search_friend

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SearchFriendViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private var _searchFriendState by mutableStateOf<SearchFriendState>(SearchFriendState.IdleState)

    val searchFriendState : State<SearchFriendState> get() = derivedStateOf { _searchFriendState }

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

                        _searchFriendState = SearchFriendState.DataState(
                            data = userModel
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error happen : ${e.message}"

                        _searchFriendState = SearchFriendState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
        }
    }
}