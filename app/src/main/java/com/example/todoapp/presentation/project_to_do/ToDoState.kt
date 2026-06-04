package com.example.todoapp.presentation.project_to_do

sealed class ToDoState {
    object LoadingState : ToDoState()
    object IdleState : ToDoState()
    data class ErrorState(val errMsg : String) : ToDoState()
}