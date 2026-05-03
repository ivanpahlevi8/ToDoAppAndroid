package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CreateTeamDto(
    @SerializedName("teamName")
    val teamName : String,

    @SerializedName("teamDescription")
    val teamDescription : String,

    @SerializedName("teamLeader")
    val teamLeaderId : String,
)
