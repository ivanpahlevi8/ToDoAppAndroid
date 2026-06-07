package com.example.todoapp.domain.usecase.todo_usecase

import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.domain.repositories.ToDoRepository

class GetToDoWithinProjectUseCase(
    private val toDoRepository: ToDoRepository
) {
    suspend operator fun invoke(
        projectId : Int
    ) : List<CreateToDoDto> {
        return toDoRepository.getToDoWithinProject(
            projectId
        )
    }
}