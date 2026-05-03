package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.AssignUserDto
import com.example.todoapp.data.dtos.CreateTeamDto
import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.domain.models.teams.TeamModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TeamRemoteAPI {
    @POST("api/Team/create-team")
    suspend fun createTeam(
        @Body teamDto: CreateTeamDto
    ) : ResponseDto<TeamModel>

    @GET("api/Team/get-all-team")
    suspend fun getAllTeam(
        @Query("userId") userId : String
    ) : ResponseDto<List<TeamModel>>

    @GET("api/Team/get-team")
    suspend fun getTeam(
        @Query("teamId") teamId : String
    ) : ResponseDto<TeamModel>

    @POST("api/Team/assign-user-team")
    suspend fun assignUserTeam(
        @Query("userId") userId: String,
        @Query("teamId") teamId : Int
    ) : ResponseDto<AssignUserDto>
}