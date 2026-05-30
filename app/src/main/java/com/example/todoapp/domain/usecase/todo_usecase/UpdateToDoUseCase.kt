package com.example.todoapp.domain.usecase.todo_usecase

import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.domain.repositories.ToDoRepository

class UpdateToDoUseCase(
    private val toDoRepository: ToDoRepository
) {
    suspend operator fun invoke(
        toDoDto: CreateToDoDto
    ) : String {
        return toDoRepository.updateToDo(
            createToDoDto = toDoDto
        )
    }
}