package com.example.todoapp.presentation.search_friend

sealed class SearchFriendEvent {
    data class OnSearch(val userName : String) : SearchFriendEvent()
}