package com.example.todoapp.domain.usecase.authorization_usecase

data class AuthUseCase(
    val loginUserUseCase: LoginUserUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val getUserIdUseCase: GetUserIdUseCase
)
