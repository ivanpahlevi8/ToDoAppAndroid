package com.example.todoapp.domain.usecase.todo_socket_usecase

import com.example.todoapp.domain.repositories.ToDoSocketRepository
import okhttp3.WebSocket

class DisConnectToServerUseCase(
    private val toDoSocketRepository: ToDoSocketRepository
) {
    suspend operator fun invoke(
        webSocket: WebSocket
    ) {
        toDoSocketRepository.disconnectToServer(
            socket = webSocket
        )
    }
}