package com.example.todoapp.data.repositories

import com.example.todoapp.core.value.Constants
import com.example.todoapp.domain.repositories.ToDoSocketRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.TimeUnit

class ToDoSocketRepositoryImpl : ToDoSocketRepository {
    private val client = OkHttpClient.Builder()
        .pingInterval(5, TimeUnit.SECONDS) // Send a ping every 5 seconds
        .build()

    override suspend fun connectToServer(
        projectId: Int,
        socketListener: WebSocketListener
    ): WebSocket {
        val request = Request.Builder()
            .url("wss://ivan-portofolio.xyz/socket/ws?projectId=$projectId")
            .build()

        val webSocket = client.newWebSocket(
            request = request,
            listener = socketListener
        )

        return webSocket
    }

    override suspend fun disconnectToServer(socket: WebSocket) {
        socket.close(1000, "User disconnected")

        client.dispatcher.executorService.shutdown()
    }
}