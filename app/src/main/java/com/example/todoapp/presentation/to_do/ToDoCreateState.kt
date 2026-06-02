package com.example.todoapp.presentation.to_do

sealed class ToDoCreateState {
    object LoadingState : ToDoCreateState()
    object IdleState : ToDoCreateState()
}