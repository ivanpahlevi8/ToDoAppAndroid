package com.example.todoapp.domain.usecase.authorization_usecase

import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.repositories.AuthRemoteRepository

class GetUserUseCase(
    private val authRemoteRepository: AuthRemoteRepository
) {
    suspend operator fun invoke(
        userName : String
    ) : UserModel {
        return authRemoteRepository.GetUserByName(
            userName = userName
        )
    }
}