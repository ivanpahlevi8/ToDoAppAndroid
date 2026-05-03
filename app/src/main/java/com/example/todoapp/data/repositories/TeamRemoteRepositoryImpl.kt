package com.example.todoapp.data.repositories

import android.util.Log
import com.example.todoapp.data.dtos.AssignUserDto
import com.example.todoapp.data.dtos.CreateTeamDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.remote.TeamRemoteAPI
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.domain.repositories.TeamRemoteRepository
import com.google.gson.Gson
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

class TeamRemoteRepositoryImpl(
    private val teamRemoteAPI: TeamRemoteAPI,
) : TeamRemoteRepository {
    override suspend fun createTeam(createTeamDto: CreateTeamDto): TeamModel {
        try{
            val response = teamRemoteAPI.createTeam(
                teamDto = createTeamDto
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

    override suspend fun getAllTeam(userId: String): List<TeamModel> {
        try{
            Log.d("CHECK", "check on user id : $userId")
            val response = teamRemoteAPI.getAllTeam(
                userId = userId
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
            Log.e("API_ERROR", "Failed to fetch teams", e)

            if (e is CancellationException) throw e

            throw Exception(e.message ?: "An unexpected network error occurred.")
        }
    }

    override suspend fun getTeam(teamId: Int): TeamModel {
        try{
            val response = teamRemoteAPI.getTeam(
                teamId = teamId.toString()
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

    override suspend fun assignUserTeam(teamId: Int, userId: String): AssignUserDto {
        try{
            val response = teamRemoteAPI.assignUserTeam(
                userId = userId,
                teamId = teamId
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