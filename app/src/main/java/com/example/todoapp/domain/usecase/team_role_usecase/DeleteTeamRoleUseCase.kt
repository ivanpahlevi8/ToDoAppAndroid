package com.example.todoapp.domain.usecase.team_role_usecase

import com.example.todoapp.domain.repositories.TeamRoleRemoteRepository

class DeleteTeamRoleUseCase(
    private val teamRoleRemoteRepository: TeamRoleRemoteRepository
) {
    suspend operator fun invoke(
        teamRoleId : Int
    ) : String {
        return teamRoleRemoteRepository.DeleteTeamRole(
            teamRoleId = teamRoleId
        )
    }
}