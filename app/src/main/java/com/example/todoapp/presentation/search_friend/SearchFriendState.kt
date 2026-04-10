package com.example.todoapp.presentation.search_friend

import com.example.todoapp.domain.models.UserModel

sealed class SearchFriendState {
    data class DataState(val data : UserModel) : SearchFriendState()
    data class ErrorState(val errMsg : String) : SearchFriendState()
    object LoadingState : SearchFriendState()
    object IdleState : SearchFriendState()
}