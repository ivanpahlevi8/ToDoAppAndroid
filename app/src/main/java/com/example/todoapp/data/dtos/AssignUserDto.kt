package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class AssignUserDto(
    @SerializedName("teamId")
    val teamId : Int,

    @SerializedName("userId")
    val userId : String,
)
