package com.example.todoapp.domain.usecase.team_usecase

data class TeamUseCase(
    val createTeamUseCase: CreateTeamUseCase,
    val getAllTeamUseCase: GetAllTeamUseCase,
    val getTeamUseCase: GetTeamUseCase,
    val assignUserTeamUseCase: AssignUserTeamUseCase,
)
