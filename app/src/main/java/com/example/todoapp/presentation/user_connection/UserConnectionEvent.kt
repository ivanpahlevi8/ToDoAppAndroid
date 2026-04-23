package com.example.todoapp.presentation.user_connection

sealed class UserConnectionEvent {
    object OnGetRequestConnection : UserConnectionEvent()
    object OnGetUserConnection : UserConnectionEvent()
    data class OnAcceptConnection(val connectionId : String) : UserConnectionEvent()
    data class OnDisconnectUserConnection(val connectionId : String) : UserConnectionEvent()
    data class OnDeclineUserConnection(val connectionId : String) : UserConnectionEvent()
    data class OnUnFollowConnection(val connectionId: String) : UserConnectionEvent()
}