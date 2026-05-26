package com.example.todoapp.presentation.team_project

import com.example.todoapp.data.dtos.CreateProjectDto

sealed class TeamProjectEvent {
    data class CreateProjectTeam(val createProjectDto: CreateProjectDto) : TeamProjectEvent()
}