package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class UnConnectUserUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(
        connectionId : Int
    ) : String {
        return connectionRemoteRepository.unConnectUser(
            connectionId = connectionId
        )
    }
}