package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.AssignUserDto
import com.example.todoapp.data.dtos.CreateTeamDto
import com.example.todoapp.domain.models.teams.TeamModel

interface TeamRemoteRepository {
    suspend fun createTeam(createTeamDto: CreateTeamDto) : TeamModel
    suspend fun getAllTeam(userId : String) : List<TeamModel>
    suspend fun getTeam(teamId : Int) : TeamModel
    suspend fun assignUserTeam(teamId : Int, userId : String, teamRoleId : Int) : AssignUserDto
    suspend fun checkMemberOnTeam(userId: String, teamId: Int) : Boolean
}