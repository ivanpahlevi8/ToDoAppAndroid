package com.example.todoapp.domain.models.teams

import com.example.todoapp.domain.models.UserModel
import com.google.gson.annotations.SerializedName

data class TeamModel(
    @SerializedName(value = "teamId")
    val teamId : Int? = -1,

    @SerializedName(value = "teamName")
    val teamName : String? = "",

    @SerializedName(value = "teamDescription")
    val teamDescription : String? = "",

    @SerializedName(value = "teamLeader")
    val teamLeaderId : String? = "",

    @Transient
    var teamLeader : UserModel?,

    @SerializedName(value = "userMember")
    val teamUserMember : List<UserModel>? = listOf()
)
