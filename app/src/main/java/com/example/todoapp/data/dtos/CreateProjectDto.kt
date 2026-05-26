package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CreateProjectDto(
    @SerializedName("projectId")
    var projectId : Int? = null,

    @SerializedName(value = "projectName")
    var projectName : String,

    @SerializedName(value = "projectDescription")
    var projectDescription : String,

    @SerializedName(value = "projectUserLeadId")
    var projectUserLeadId : String? = null,

    @SerializedName(value = "projectTeamId")
    var projectTeamId : Int? = null,

    @SerializedName(value = "projectStatus")
    var projectStatus : String? = null,

    @SerializedName(value = "createdAt")
    var projectCreatedAt : String? = null
)
