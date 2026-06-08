package com.example.todoapp.data.dtos

data class ToDoPointerDto(
    var toDoPointerStatus : String,
    var targetToDoState : String? = null,
    var toDoItem : CreateToDoDto
)
