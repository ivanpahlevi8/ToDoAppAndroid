package com.example.todoapp.data.repositories

import android.util.Log
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.remote.ToDoRemoteAPI
import com.example.todoapp.domain.repositories.ToDoRepository
import com.google.gson.Gson
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

class ToDoRepositoryImpl(
    private val toDoRemoteAPI: ToDoRemoteAPI
) : ToDoRepository {
    override suspend fun createToDo(createToDoDto: CreateToDoDto): CreateToDoDto {
        try{
            val response = toDoRemoteAPI.createToDo(
                createToDoDto = createToDoDto
            )

            return response.responseResult
        } catch (e: HttpException) {
            val errorBodyString = e.response()?.errorBody()?.string()

            if (errorBodyString != null) {
                try {
                    val parsedError = Gson().fromJson(errorBodyString, ResponseDto::class.java)
                    throw Exception(parsedError.responseMessage)

                } catch (jsonException: Exception) {
                    Log.d("Check", errorBodyString)
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

    override suspend fun updateToDo(createToDoDto: CreateToDoDto): String {
        try{
            val response = toDoRemoteAPI.updateToDo(
                createToDoDto = createToDoDto
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