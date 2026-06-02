package com.example.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CreateToDoDto(
    @SerializedName(value = "toDoId")
    var toDoId : Int? = null,
    
    @SerializedName(value = "projectId")
    var toDoProjectId : Int? = null,

    @SerializedName(value = "itemName")
    var toDoItemName : String,

    @SerializedName(value = "itemDescription")
    var toDoItemDescription : String,

    @SerializedName(value = "itemState")
    var toDoItemState : String? = null,

    @SerializedName(value = "createdAt")
    var toDoCreatedAt : String? = null
)
