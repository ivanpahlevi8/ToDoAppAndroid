package com.example.todoapp.presentation.user_connection

sealed class UserConnectionState {
    data class DataState<T>(val data : T) : UserConnectionState()
    data class ErrorState(val errMsg : String) : UserConnectionState()
    object LoadingState : UserConnectionState()
    object IdleState : UserConnectionState()
}