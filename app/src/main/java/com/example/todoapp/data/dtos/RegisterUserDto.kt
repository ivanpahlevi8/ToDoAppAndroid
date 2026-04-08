package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class RegisterUserDto(
    @SerializedName(value = "userName")
    val userName : String,

    @SerializedName(value = "password")
    val password : String,

    @SerializedName(value = "email")
    val email : String,

    @SerializedName(value = "phoneNumber")
    val phoneNumber : String,

    @SerializedName(value = "firstName")
    val firstName : String,

    @SerializedName(value = "lastName")
    val lastName : String,
)
