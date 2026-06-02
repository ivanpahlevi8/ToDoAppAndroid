package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.CreateToDoDto

interface ToDoRepository {
    suspend fun createToDo(createToDoDto: CreateToDoDto) : CreateToDoDto
    suspend fun updateToDo(createToDoDto: CreateToDoDto) : String
}