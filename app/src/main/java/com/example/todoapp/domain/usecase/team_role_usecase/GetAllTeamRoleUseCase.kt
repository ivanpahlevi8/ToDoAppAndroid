package com.example.todoapp.domain.usecase.team_role_usecase

import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.domain.repositories.TeamRoleRemoteRepository

class GetAllTeamRoleUseCase(
    private val teamRoleRemoteRepository: TeamRoleRemoteRepository
) {
    suspend operator fun invoke(
        teamId : Int
    ) : List<TeamRoleDto> {
        return teamRoleRemoteRepository.GetAllTeamRole(
            teamId = teamId
        )
    }
}