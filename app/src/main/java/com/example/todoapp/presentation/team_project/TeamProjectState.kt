package com.example.todoapp.presentation.team_project

import com.example.todoapp.domain.models.UserModel

sealed class TeamProjectState {
    data class DataState<T>(val data : T, val userList : List<UserModel>?=null) : TeamProjectState()
    data class ErrorState(val errMsg : String) : TeamProjectState()
    object LoadingState : TeamProjectState()
}