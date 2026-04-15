package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("message")
    val responseMessage : String?,

    @SerializedName("isSuccess")
    val responseIsSuccess : Boolean?,

    @SerializedName("result")
    val responseResult : T,
)
