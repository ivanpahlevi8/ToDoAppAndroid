package com.example.todoapp.presentation.team_detail

import com.example.todoapp.data.dtos.TeamRoleDto

sealed class TeamDetailEvent {
    data class OnAddTeamRole(val teamRoleDto: TeamRoleDto) : TeamDetailEvent()
    data class OnDeleteTeamRole(val teamRoleId : Int) : TeamDetailEvent()
    data class OnAddTeamMember(val userId : String, val teamId : Int, val teamRole : Int) : TeamDetailEvent()
    data class OnRemoveTeamMember(val userId : String, val teamId: Int) : TeamDetailEvent()
    data class OnSearchConnection(val name : String) : TeamDetailEvent()
}