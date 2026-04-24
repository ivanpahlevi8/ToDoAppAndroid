package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class GetIsConnectedStatusUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(
        userId : String,
        userConnectionId : String
    ) : Boolean {
        return connectionRemoteRepository.getIsConnectedStatus(
            userId = userId,
            userConnectionId = userConnectionId
        )
    }
}