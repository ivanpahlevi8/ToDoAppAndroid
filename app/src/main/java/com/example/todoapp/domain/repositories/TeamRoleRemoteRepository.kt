package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.TeamRoleDto

interface TeamRoleRemoteRepository {
    suspend fun CreateTeamRole(teamRoleDto: TeamRoleDto) : TeamRoleDto
    suspend fun DeleteTeamRole(teamRoleId : Int) : String
    suspend fun GetAllTeamRole(teamId : Int) : List<TeamRoleDto>
}