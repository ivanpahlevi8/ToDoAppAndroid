package com.example.todoapp.domain.usecase.authorization_usecase

import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.repositories.AuthRemoteRepository

class GetUserIdUseCase(
    private val authRemoteRepository: AuthRemoteRepository
) {
    suspend operator fun invoke(userId : String) : UserModel {
        return authRemoteRepository.GetUserById(
            userId = userId
        )
    }
}