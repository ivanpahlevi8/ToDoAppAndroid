package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.domain.models.SendConnectionModel
import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class SearchConnectionUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(
        name : String,
        userId : String
    ) : List<SendConnectionModel>{
        return connectionRemoteRepository.searchConnection(
            name = name,
            userId = userId
        )
    }
}