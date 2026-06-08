package com.example.todoapp.presentation.project_to_do

import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto

sealed class ToDoEvent {
    data class CreateToDo(val toDoDto: CreateToDoDto) : ToDoEvent()
    data class UpdateToDo(val toDoPointer: ToDoPointerDto) : ToDoEvent()
    data class DeleteToDo(val toDoPointer : ToDoPointerDto?) : ToDoEvent()
    data class OnGrabbedItem(val toDoPointer: ToDoPointerDto) : ToDoEvent()
}