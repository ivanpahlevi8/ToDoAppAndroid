package com.example.todoapp.presentation.on_board_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardScreenViewModel @Inject constructor(
    val localUserManagerUseCase: LocalUserManagerUseCase
) : ViewModel() {
    private var _onBoardState by mutableStateOf<OnBoardScreenState>(OnBoardScreenState.IdleState)

    val onBoardState : State<OnBoardScreenState> get() = derivedStateOf { _onBoardState }

    fun onEvent(event : OnBoardScreenEvent) {
        when(event) {
            is OnBoardScreenEvent.BoardEvent -> {
                viewModelScope.launch {
                    _onBoardState = OnBoardScreenState.LoadingState

                    delay(600)

                    localUserManagerUseCase.setUserOnBoardUseCase()
                }
            }
        }
    }
}