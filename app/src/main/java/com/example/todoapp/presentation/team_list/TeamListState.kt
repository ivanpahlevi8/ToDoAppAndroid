package com.example.todoapp.presentation.team_list

sealed class TeamListState {
    data class DataState<T>(val data : T) : TeamListState()
    data class ErrorState(val errMsg : String) : TeamListState()
    object LoadingState : TeamListState()
    object IdleState : TeamListState()
}