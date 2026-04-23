package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.domain.models.SendConnectionModel
import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class GetRequestConnectionToUserUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(
        userId : String
    ) : List<SendConnectionModel> {
        return connectionRemoteRepository.getRequestConnectionToUser(
            userId = userId
        )
    }
}