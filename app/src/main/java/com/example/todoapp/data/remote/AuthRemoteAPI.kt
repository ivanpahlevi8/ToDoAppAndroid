package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.data.dtos.RegisterUserDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.domain.models.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthRemoteAPI {
    @POST("api/Auth/register-user")
    suspend fun RegisterUser(
        @Body registerUser : RegisterUserDto
    ) : ResponseDto<UserModel>

    @POST("api/Auth/login-user")
    suspend fun LoginUser(
        @Body loginUser : LoginUserDto
    ) : ResponseDto<UserModel>

    @GET("api/Auth/get-userName")
    suspend fun GetUserByName(
        @Query("userName") userName : String
    ) : ResponseDto<UserModel>

    @GET("api/Auth/get-userid")
    suspend fun GetUserById(
        @Query("userId") userId : String
    ) : ResponseDto<UserModel>
}