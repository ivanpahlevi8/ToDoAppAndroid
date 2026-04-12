package com.example.todoapp.domain.usecase.user_connection_usecase

import com.example.todoapp.data.dtos.SendConnectionRequestDto
import com.example.todoapp.domain.repositories.ConnectionRemoteRepository

class SendUserConnectionUseCase(
    private val connectionRemoteRepository: ConnectionRemoteRepository
) {
    suspend operator fun invoke(sendConnectionRequestDto: SendConnectionRequestDto) : String {
        return connectionRemoteRepository.sendUserConnection(
            sendConnection = sendConnectionRequestDto
        )
    }
}