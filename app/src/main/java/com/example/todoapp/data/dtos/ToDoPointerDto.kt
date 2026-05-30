package com.example.todoapp.data.dtos

data class ToDoPointerDto(
    val toDoPointerStatus : String,
    val targetToDoState : String? = null,
    val toDoItem : CreateToDoDto
)
