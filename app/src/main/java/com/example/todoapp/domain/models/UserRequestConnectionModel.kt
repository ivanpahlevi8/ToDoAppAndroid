package com.example.todoapp.domain.models

data class UserRequestConnectionModel(
    val isFromUser : Boolean,
    val connectionId : String,
    val userConnection : UserModel
)
