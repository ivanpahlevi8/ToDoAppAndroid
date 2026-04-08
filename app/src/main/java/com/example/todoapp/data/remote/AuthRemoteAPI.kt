package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.data.dtos.RegisterUserDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.domain.models.UserModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRemoteAPI {
    @POST("api/Auth/register-user")
    suspend fun RegisterUser(
        @Body registerUser : RegisterUserDto
    ) : ResponseDto<UserModel>

    @POST("api/Auth/login-user")
    suspend fun LoginUser(
        @Body loginUser : LoginUserDto
    ) : ResponseDto<UserModel>
}