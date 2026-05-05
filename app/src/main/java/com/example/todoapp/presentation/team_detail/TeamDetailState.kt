package com.example.todoapp.presentation.team_detail

sealed class TeamDetailState {
    data class DataState<T>(val data : T) : TeamDetailState()
    data class ErrorState(val errMsg : String) : TeamDetailState()
    object LoadingState : TeamDetailState()
}