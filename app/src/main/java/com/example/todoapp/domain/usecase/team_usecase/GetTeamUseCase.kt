package com.example.todoapp.domain.usecase.team_usecase

import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.domain.repositories.TeamRemoteRepository

class GetTeamUseCase(
    private val teamRemoteRepository: TeamRemoteRepository
) {
    suspend operator fun invoke(
        teamId : Int
    ) : TeamModel {
        return teamRemoteRepository.getTeam(
            teamId = teamId
        )
    }
}