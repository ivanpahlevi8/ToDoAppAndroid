package com.example.todoapp.data.repositories

import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.data.dtos.RegisterUserDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.remote.AuthRemoteAPI
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.repositories.AuthRemoteRepository
import com.google.gson.Gson
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

class AuthRemoteRepositoryImpl(
    private val authRemoteAPI: AuthRemoteAPI
) : AuthRemoteRepository {
    override suspend fun RegisterUser(registerUserDto: RegisterUserDto): UserModel {
        try {
            val response: ResponseDto<UserModel> = authRemoteAPI.RegisterUser(
                registerUser = registerUserDto
            )

            return response.responseResult

        } catch (e: HttpException) {
            val errorBodyString = e.response()?.errorBody()?.string()

            if (errorBodyString != null) {
                try {
                    val parsedError = Gson().fromJson(errorBodyString, ResponseDto::class.java)
                    throw Exception(parsedError.responseMessage)

                } catch (jsonException: Exception) {
                    throw Exception(jsonException.message ?: "Failed to parse error response")
                }
            } else {
                throw Exception("Unknown server error occurred.")
            }

        } catch (e: Exception) {
            if (e is CancellationException) throw e

            throw Exception(e.message ?: "An unexpected network error occurred.")
        }
    }

    override suspend fun LoginUser(loginUserDto: LoginUserDto) : UserModel {
        try{
            val response : ResponseDto<UserModel> = authRemoteAPI.LoginUser(
                loginUser = loginUserDto
            )

            return response.responseResult
        } catch (e: HttpException) {
            val errorBodyString = e.response()?.errorBody()?.string()

            if (errorBodyString != null) {
                try {
                    val parsedError = Gson().fromJson(errorBodyString, ResponseDto::class.java)
                    throw Exception(parsedError.responseMessage)

                } catch (jsonException: Exception) {
                    throw Exception(jsonException.message ?: "Failed to parse error response")
                }
            } else {
                throw Exception("Unknown server error occurred.")
            }

        } catch (e: Exception) {
            if (e is CancellationException) throw e

            throw Exception(e.message ?: "An unexpected network error occurred.")
        }
    }

    override suspend fun GetUserByName(userName: String): UserModel {
        try{
            val response = authRemoteAPI.GetUserByName(
                userName = userName
            )

            return response.responseResult
        } catch (e: HttpException) {
            val errorBodyString = e.response()?.errorBody()?.string()

            if (errorBodyString != null) {
                try {
                    val parsedError = Gson().fromJson(errorBodyString, ResponseDto::class.java)
                    throw Exception(parsedError.responseMessage)

                } catch (jsonException: Exception) {
                    throw Exception(jsonException.message ?: "Failed to parse error response")
                }
            } else {
                throw Exception("Unknown server error occurred.")
            }

        } catch (e: Exception) {
            if (e is CancellationException) throw e

            throw Exception(e.message ?: "An unexpected network error occurred.")
        }
    }
}