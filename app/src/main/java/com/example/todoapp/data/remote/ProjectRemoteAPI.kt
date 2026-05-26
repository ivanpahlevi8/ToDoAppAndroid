package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.data.dtos.ResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProjectRemoteAPI {
    @POST("api/Project/create-project")
    suspend fun createProjectWithinTeam(
        @Body createProjectDto : CreateProjectDto
    ) : ResponseDto<String>

    @GET("api/Project/get-all-project-byteam")
    suspend fun getAllProjectWithinTeam(
        @Query("teamId") teamId : Int
    ) : ResponseDto<List<CreateProjectDto>>
}