package com.example.todoapp.presentation.team_detail

import com.example.todoapp.data.dtos.TeamRoleDto

sealed class TeamDetailEvent {
    data class OnAddTeamRole(val teamRoleDto: TeamRoleDto) : TeamDetailEvent()
    data class OnDeleteTeamRole(val teamRoleId : Int) : TeamDetailEvent()
}