package com.example.todoapp.presentation.team_list

import com.example.todoapp.data.dtos.CreateTeamDto

sealed class TeamListEvent {
    data class OnAddTeam(val createTeamDto: CreateTeamDto) : TeamListEvent()
}