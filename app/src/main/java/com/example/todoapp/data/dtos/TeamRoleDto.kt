package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class TeamRoleDto(
    @SerializedName(value = "teamRoleId")
    val teamRoleId : Int? = null,

    @SerializedName(value = "roleName")
    val roleName : String,

    @SerializedName(value = "teamId")
    val teamId : Int,

    @SerializedName(value = "createdAt")
    val createdAt : String? = null,
)
