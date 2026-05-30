package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CreateToDoDto(
    @SerializedName(value = "toDoId")
    val toDoId : Int? = null,
    
    @SerializedName(value = "projectId")
    val toDoProjectId : Int? = null,

    @SerializedName(value = "itemName")
    val toDoItemName : String,

    @SerializedName(value = "itemDescription")
    val toDoItemDescription : String,

    @SerializedName(value = "itemState")
    val toDoItemState : String? = null,

    @SerializedName(value = "createdAt")
    val toDoCreatedAt : String? = null
)
