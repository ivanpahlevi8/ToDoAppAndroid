package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class SendConnectionRequestDto(
    @SerializedName(value = "userOwnerId")
    val userOwnerId : String,

    @SerializedName(value = "userConnectionId")
    val userConnectionId : String,
)
