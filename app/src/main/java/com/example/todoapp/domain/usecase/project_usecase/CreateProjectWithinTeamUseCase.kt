package com.example.todoapp.domain.usecase.project_usecase

import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.domain.repositories.ProjectRemoteRepository

class CreateProjectWithinTeamUseCase(
    private val projectRemoteRepository: ProjectRemoteRepository
) {
    suspend operator fun invoke(
        createProjectDto: CreateProjectDto
    ) : String {
        return projectRemoteRepository.createProjectWithinTeam(
            createProjectDto = createProjectDto
        )
    }
}