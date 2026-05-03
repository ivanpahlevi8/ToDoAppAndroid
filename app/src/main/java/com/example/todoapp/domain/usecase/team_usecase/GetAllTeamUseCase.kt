package com.example.todoapp.domain.usecase.team_usecase

import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.domain.repositories.TeamRemoteRepository

class GetAllTeamUseCase(
    private val teamRemoteRepository: TeamRemoteRepository
) {
    suspend operator fun invoke(
        userId : String
    ) : List<TeamModel> {
        return teamRemoteRepository.getAllTeam(
            userId = userId
        )
    }
}