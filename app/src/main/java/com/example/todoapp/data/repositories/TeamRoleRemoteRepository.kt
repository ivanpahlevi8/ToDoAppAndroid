package com.example.todoapp.data.repositories

import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.data.remote.TeamRoleRemoteAPI
import com.example.todoapp.domain.repositories.TeamRoleRemoteRepository
import com.google.gson.Gson
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

class TeamRoleRemoteRepository(
    private val teamRoleRemoteAPI: TeamRoleRemoteAPI
) : TeamRoleRemoteRepository {
    override suspend fun CreateTeamRole(teamRoleDto: TeamRoleDto): TeamRoleDto {
        try{
            val response : ResponseDto<TeamRoleDto> = teamRoleRemoteAPI.CreateTeamRole(
                teamRole = teamRoleDto
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

    override suspend fun DeleteTeamRole(teamRoleId: Int): String {
        try{
            val response : ResponseDto<String> = teamRoleRemoteAPI.DeleteTeamRole(
                teamRoleId = teamRoleId
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

    override suspend fun GetAllTeamRole(teamId: Int): List<TeamRoleDto> {
        try{
            val response : ResponseDto<List<TeamRoleDto>> = teamRoleRemoteAPI.GetAllTeamRole(
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