package com.example.todoapp.presentation.team_detail

import com.example.todoapp.data.dtos.TeamRoleDto

sealed class TeamDetailState {
    data class DataState<T>(val data : T, val roleModel : List<TeamRoleDto>?) : TeamDetailState()
    data class ErrorState(val errMsg : String) : TeamDetailState()
    object LoadingState : TeamDetailState()
    object IdleState : TeamDetailState()
}