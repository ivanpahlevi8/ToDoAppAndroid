package com.example.todoapp.domain.usecase.project_usecase

import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.domain.repositories.ProjectRemoteRepository

class GetProjectDetailUseCase(
    private val projectRemoteRepository: ProjectRemoteRepository
) {
    suspend operator fun invoke(
        projectId : Int
    ) : CreateProjectDto {
        return projectRemoteRepository.getDetailProject(
            projectId = projectId
        )
    }
}