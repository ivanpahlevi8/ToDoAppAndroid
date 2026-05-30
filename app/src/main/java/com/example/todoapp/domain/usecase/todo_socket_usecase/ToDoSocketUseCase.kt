package com.example.todoapp.domain.usecase.todo_socket_usecase

data class ToDoSocketUseCase(
    val connectToServerUseCase: ConnectToServerUseCase,
    val disConnectToServerUseCase: DisConnectToServerUseCase
)
