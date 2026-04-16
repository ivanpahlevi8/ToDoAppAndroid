package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.SendConnectionRequestDto
import com.example.todoapp.domain.models.SendConnectionModel

interface ConnectionRemoteRepository {
    suspend fun sendUserConnection(sendConnection : SendConnectionRequestDto) : String
    suspend fun acceptUserConnection(connectionId : Int) : SendConnectionModel
    suspend fun getRequestConnection(userRequesterId : String) : List<SendConnectionModel>
    suspend fun getAllRequestConnection(userId : String) : List<SendConnectionModel>
    suspend fun unConnectUser(connectionId : Int) : String
    suspend fun declineUser(connectionId: Int) : String
    suspend fun getConnectionRejectedByUser(userId : String) : List<SendConnectionModel>
    suspend fun getConnectionRejectToUser(userId: String) : List<SendConnectionModel>
    suspend fun getConnectionDisconnectedByUser(userId: String) : List<SendConnectionModel>
    suspend fun getConnectionDisconnectedToUser(userId : String) : List<SendConnectionModel>
}