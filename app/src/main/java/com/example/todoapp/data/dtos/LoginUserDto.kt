package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class LoginUserDto(
    @SerializedName(value = "username")
    val username : String,

    @SerializedName(value = "password")
    val password : String,
)
