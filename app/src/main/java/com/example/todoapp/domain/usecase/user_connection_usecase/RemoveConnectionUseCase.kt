package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.domain.models.SendConnectionModel
import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class RemoveConnectionUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(
        connectionId : Int
    ) : SendConnectionModel {
        return connectionRemoteRepository.removeConnection(
            connectionId = connectionId
        )
    }
}