package com.example.todoapp.domain.usecase.team_usecase

import com.example.todoapp.data.dtos.CreateTeamDto
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.domain.repositories.TeamRemoteRepository

class CreateTeamUseCase(
    private val teamRemoteRepository: TeamRemoteRepository
) {
    suspend operator fun invoke(
        createTeamDto: CreateTeamDto
    ) : TeamModel {
        return teamRemoteRepository.createTeam(
            createTeamDto = createTeamDto
        )
    }
}