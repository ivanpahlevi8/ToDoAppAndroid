package com.example.todoapp.domain.usecase.team_usecase

import com.example.todoapp.domain.repositories.TeamRemoteRepository

class UnAssignUserTeamUseCase(
    private val teamRemoteRepository: TeamRemoteRepository
) {
    suspend operator fun invoke(
        userId : String,
        teamId : Int
    ) : String {
        return teamRemoteRepository.unAssignUserTeam(
            userId = userId,
            teamId = teamId
        )
    }
}