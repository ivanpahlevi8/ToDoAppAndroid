package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.data.dtos.RegisterUserDto
import com.example.todoapp.domain.models.UserModel

interface AuthRemoteRepository {
    suspend fun RegisterUser(registerUserDto: RegisterUserDto) : UserModel
    suspend fun LoginUser(loginUserDto: LoginUserDto) : UserModel
    suspend fun GetUserByName(userName : String) : UserModel
}