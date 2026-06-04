package com.example.todoapp.presentation.project_to_do

sealed class ToDoPointerState(
    val name : String
) {
    object Grabbed : ToDoPointerState("GRABBED")
    object Dropped : ToDoPointerState("DROPPEPD")
    object Released : ToDoPointerState("RELEASED")
    object Created : ToDoPointerState("CREATED")
}