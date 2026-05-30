package com.example.todoapp.domain.usecase.todo_usecase

import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.domain.repositories.ToDoRepository

class CreateToDoUseCase(
    private val toDoRepository: ToDoRepository
) {
    suspend operator fun invoke(
        createToDoDto: CreateToDoDto
    ) : String {
        return toDoRepository.createToDo(
            createToDoDto = createToDoDto
        )
    }
}