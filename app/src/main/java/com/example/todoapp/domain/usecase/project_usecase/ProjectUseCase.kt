package com.example.todoapp.domain.usecase.project_usecase

data class ProjectUseCase(
    val createProjectWithinTeamUseCase: CreateProjectWithinTeamUseCase,
    val getProjectWithinTeamUseCase: GetProjectWithinTeamUseCase,
    val getProjectDetailUseCase: GetProjectDetailUseCase,
)
