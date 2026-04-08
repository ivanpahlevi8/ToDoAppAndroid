package com.example.todoapp.presentation.authentication

import com.example.todoapp.domain.models.UserModel

sealed class AuthState {
    object LoadingState : AuthState()
    object IdleState : AuthState()
    data class DataState(val data : UserModel) : AuthState()
    data class ErrorState(val errMsg : String) : AuthState()
}