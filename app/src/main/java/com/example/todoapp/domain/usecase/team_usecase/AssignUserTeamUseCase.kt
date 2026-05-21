package com.example.todoapp.domain.usecase.team_usecase

import com.example.todoapp.data.dtos.AssignUserDto
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.domain.repositories.TeamRemoteRepository

class AssignUserTeamUseCase(
    private val teamRemoteRepository: TeamRemoteRepository
) {
    suspend operator fun invoke(
        userId : String,
        teamId : Int,
        teamRoleId : Int
    ) : AssignUserDto {
        return teamRemoteRepository.assignUserTeam(
            teamId = teamId,
            userId = userId,
            teamRoleId = teamRoleId
        )
    }
}