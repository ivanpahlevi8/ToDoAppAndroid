package com.example.todoapp.domain.usecase.team_usecase

import com.example.todoapp.domain.repositories.TeamRemoteRepository

class CheckUserOnTeamUseCase(
    private val teamRemoteRepository: TeamRemoteRepository
) {
    suspend operator fun invoke(
        userId : String,
        teamId : Int
    ) : Boolean {
        return teamRemoteRepository.checkMemberOnTeam(
            userId = userId,
            teamId = teamId
        )
    }
}