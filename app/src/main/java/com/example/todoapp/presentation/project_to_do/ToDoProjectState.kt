package com.example.todoapp.presentation.project_to_do

sealed class ToDoProjectState {
    data class DataState<T>(val data : T) : ToDoProjectState()
    data class ErrorState(val errMsg : String) : ToDoProjectState()
    object LoadingState : ToDoProjectState()
    object IdleState : ToDoProjectState()
}