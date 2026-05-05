package com.example.todoapp.domain.usecase.team_role_usecase

import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.domain.repositories.TeamRoleRemoteRepository

class CreateTeamRoleUseCase(
    private val teamRoleRemoteRepository: TeamRoleRemoteRepository
) {
    suspend operator fun invoke(
        teamRoleDto: TeamRoleDto
    ) : TeamRoleDto {
        return teamRoleRemoteRepository.CreateTeamRole(
            teamRoleDto = teamRoleDto,
        )
    }
}