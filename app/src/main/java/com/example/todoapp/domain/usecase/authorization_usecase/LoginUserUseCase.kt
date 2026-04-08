package com.example.todoapp.domain.usecase.authorization_usecase

import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.repositories.AuthRemoteRepository

class LoginUserUseCase(
    private val authRemoteRepository: AuthRemoteRepository
) {
    suspend operator fun invoke(loginUserDto: LoginUserDto) : UserModel {
        return authRemoteRepository.LoginUser(
            loginUserDto = loginUserDto
        )
    }
}