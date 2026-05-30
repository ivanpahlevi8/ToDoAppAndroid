package com.example.todoapp.domain.repositories

import okhttp3.WebSocket
import okhttp3.WebSocketListener

interface ToDoSocketRepository {
    suspend fun connectToServer(projectId : Int, socketListener : WebSocketListener) : WebSocket
    suspend fun disconnectToServer(socket : WebSocket)
}