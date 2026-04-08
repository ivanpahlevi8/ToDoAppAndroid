package com.example.todoapp.presentation.authentication

import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.data.dtos.RegisterUserDto

sealed class AuthEvent {
    data class OnLoginEvent(val loginUserDto: LoginUserDto) : AuthEvent()
    data class OnRegisterEvent(val registerUserDto: RegisterUserDto) : AuthEvent()
}