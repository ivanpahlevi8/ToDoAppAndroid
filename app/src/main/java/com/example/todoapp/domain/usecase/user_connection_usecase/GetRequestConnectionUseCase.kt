package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.domain.models.SendConnectionModel
import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class GetRequestConnectionUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(userRequesterId : String) : List<SendConnectionModel>{
        return connectionRemoteRepository.getRequestConnection(
            userRequesterId = userRequesterId
        )
    }
}