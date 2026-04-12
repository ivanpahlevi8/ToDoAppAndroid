package com.example.todoapp.domain.models

import com.google.gson.annotations.SerializedName

data class SendConnectionModel(
    @SerializedName(value = "connectionId")
    val connectionId : Int,

    @SerializedName(value = "userOwnerId")
    val connectionUserOwnerId : String,

    @SerializedName(value = "userConnectionId")
    val connectionUserConnectionId : String,

    @SerializedName(value = "connectionStatus")
    val connectionStatus : String,

    @SerializedName(value = "createdAt")
    val connectionCreatedAt : String

)