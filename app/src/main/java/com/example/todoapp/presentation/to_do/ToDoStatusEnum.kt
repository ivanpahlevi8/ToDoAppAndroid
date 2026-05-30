package com.example.todoapp.presentation.to_do

enum class ToDoStatusEnum(
    val label : String
) {
    CREATED("CREATED_TO_DO"),
    PROCESSED("PROCESSED_TO_DO"),
    FINISHED("FINISHED_TO_DO")
}