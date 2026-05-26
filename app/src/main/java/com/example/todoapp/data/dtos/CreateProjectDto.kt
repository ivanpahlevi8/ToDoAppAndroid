package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CreateProjectDto(
    @SerializedName("projectId")
    val projectId : Int? = null,

    @SerializedName(value = "projectName")
    val projectName : String,

    @SerializedName(value = "projectDescription")
    val projectDescription : String,

    @SerializedName(value = "projectUserLeadId")
    val projectUserLeadId : String? = null,

    @SerializedName(value = "projectTeamId")
    val projectTeamId : Int? = null,

    @SerializedName(value = "projectStatus")
    val projectStatus : String? = null,

    @SerializedName(value = "createdAt")
    val projectCreatedAt : String? = null
)
