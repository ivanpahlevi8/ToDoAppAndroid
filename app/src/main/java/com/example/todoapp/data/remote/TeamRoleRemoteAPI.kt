package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.dtos.TeamRoleDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface TeamRoleRemoteAPI {
    // function to create team role
    @POST("api/TeamRole/create-teamrole")
    suspend fun CreateTeamRole(
        teamRole : TeamRoleDto
    ) : ResponseDto<TeamRoleDto>

    // function to delete team role
    @DELETE("api/TeamRole/delete-teamrole")
    suspend fun DeleteTeamRole(
        teamRoleId : Int
    ) : ResponseDto<String>

    // function to get all team role
    @GET("api/TeamRole/get-teamroles")
    suspend fun GetAllTeamRole(
        teamId : Int
    ) : ResponseDto<List<TeamRoleDto>>
}