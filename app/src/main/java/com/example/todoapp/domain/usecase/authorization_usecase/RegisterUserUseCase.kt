package com.example.todoapp.domain.usecase.authorization_usecase

import com.example.todoapp.data.dtos.RegisterUserDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.repositories.AuthRemoteRepository

class RegisterUserUseCase(
    private val authRemoteRepository: AuthRemoteRepository
) {
    suspend operator fun invoke(
        registerUserDto: RegisterUserDto
    ) : UserModel {
        return authRemoteRepository.RegisterUser(
            registerUserDto = registerUserDto
        )
    }
}