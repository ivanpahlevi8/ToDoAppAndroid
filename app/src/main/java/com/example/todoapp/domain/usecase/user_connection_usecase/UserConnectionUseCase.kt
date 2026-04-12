package com.example.todoapp.domain.usecase.user_connection_usecase

data class UserConnectionUseCase(
    val sendUserConnectionUseCase: SendUserConnectionUseCase,
    val acceptUserConnectionUseCase: AcceptUserConnectionUseCase,
    val getRequestConnectionUseCase: GetRequestConnectionUseCase,
    val getAllConnectionUseCase: GetAllConnectionUseCase
)
