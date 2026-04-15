package com.example.todoapp.data.repositories

import android.util.Log
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

    override suspend fun GetUserById(userId: String): UserModel {
        try{
            Log.d("CHECK", "Start get user by id")
            Log.d("CHECK", "Get user id $userId")
            val response = authRemoteAPI.GetUserById(
                userId = userId
            )
            Log.d("CHECK", "Done get user by id : ${response}")

            return response.responseResult
        } catch (e: HttpException) {
            val errorBodyString = e.response()?.errorBody()?.string()

            // Log the RAW error so we can actually see why the server rejected the request!
            Log.e("CHECK", "HTTP Error Code: ${e.code()}")
            Log.e("CHECK", "Raw Error Body: $errorBodyString")

            val parsedError = try {
                if (errorBodyString != null) {
                    Gson().fromJson(errorBodyString, ResponseDto::class.java)
                } else null
            } catch (jsonException: Exception) {
                null // If Gson fails to parse, just return null safely
            }

            // Safely throw the message, or a fallback if parsing failed
            val errorMessage = parsedError?.responseMessage ?: "Server returned error code ${e.code()}"
            throw Exception(errorMessage)
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            throw Exception(e.message ?: "An unexpected network error occurred.")
        }
    }
}