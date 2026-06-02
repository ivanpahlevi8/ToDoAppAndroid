package com.example.todoapp.presentation.to_do

import com.example.todoapp.data.dtos.CreateToDoDto

sealed class ToDoEvent {
    data class CreateToDo(val toDoDto: CreateToDoDto) : ToDoEvent()
}