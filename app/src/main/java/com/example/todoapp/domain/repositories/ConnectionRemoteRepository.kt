package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.SendConnectionRequestDto
import com.example.todoapp.domain.models.SendConnectionModel

interface ConnectionRemoteRepository {
    suspend fun sendUserConnection(sendConnection : SendConnectionRequestDto) : String
    suspend fun acceptUserConnection(connectionId : Int) : SendConnectionModel
    suspend fun getRequestConnection(userRequesterId : String) : List<SendConnectionModel>
    suspend fun getAllRequestConnection(userId : String) : List<SendConnectionModel>
}