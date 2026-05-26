package com.example.todoapp.domain.usecase.project_usecase

import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.domain.repositories.ProjectRemoteRepository

class GetProjectWithinTeamUseCase(
    private val projectRemoteRepository: ProjectRemoteRepository
) {
    suspend operator fun invoke(
        teamId :Int
    ) : List<CreateProjectDto> {
        return projectRemoteRepository.getAllProjectWithinTeam(
            teamId = teamId
        )
    }
}