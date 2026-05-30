package com.example.todoapp.domain.usecase.todo_socket_usecase

import com.example.todoapp.domain.repositories.ToDoSocketRepository
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ConnectToServerUseCase(
    private val toDoSocketRepository: ToDoSocketRepository
) {
    suspend operator fun invoke(
        projectId : Int,
        listener : WebSocketListener
    ) : WebSocket {
        return toDoSocketRepository.connectToServer(
            projectId = projectId,
            socketListener = listener
        )
    }
}