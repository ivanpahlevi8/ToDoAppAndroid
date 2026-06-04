package com.example.todoapp.data.repositories

import android.util.Log
import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.remote.ProjectRemoteAPI
import com.example.todoapp.domain.repositories.ProjectRemoteRepository
import com.google.gson.Gson
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

class ProjectRemoteRepositoryImpl(
    private val projectRemoteAPI: ProjectRemoteAPI
) : ProjectRemoteRepository {
    override suspend fun createProjectWithinTeam(createProjectDto: CreateProjectDto): String {
        try{
            val response = projectRemoteAPI.createProjectWithinTeam(
                createProjectDto = createProjectDto
            )

            return response.responseResult
        } catch (e: HttpException) {
            val errorBodyString = e.response()?.errorBody()?.string()

            if (errorBodyString != null) {
                try {
                    val parsedError = Gson().fromJson(errorBodyString, ResponseDto::class.java)
                    throw Exception(parsedError.responseMessage)

                } catch (jsonException: Exception) {
                    Log.d("CHECK", errorBodyString)
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

    override suspend fun getAllProjectWithinTeam(teamId: Int): List<CreateProjectDto> {
        try{
            val response = projectRemoteAPI.getAllProjectWithinTeam(
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

    override suspend fun getDetailProject(projectId: Int): CreateProjectDto {
        try{
            val response = projectRemoteAPI.getProjectById(
                projectId = projectId
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