package com.example.todoapp.domain.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName(value = "firstName")
    val userFirstName : String,

    @SerializedName(value = "lastName")
    val userLastName : String,

    @SerializedName(value = "createdAt")
    val userCreatedAt : String,

    @SerializedName(value = "id")
    val userId : String,

    @SerializedName(value = "userName")
    val userName : String,

    @SerializedName(value = "email")
    val userEmail : String,

    @SerializedName(value = "phoneNumber")
    val userPhoneNumber : String,
)
