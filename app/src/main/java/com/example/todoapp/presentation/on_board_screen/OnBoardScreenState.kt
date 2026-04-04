package com.example.todoapp.presentation.on_board_screen

sealed class OnBoardScreenState{
    object LoadingState : OnBoardScreenState()
    object IdleState : OnBoardScreenState()
}