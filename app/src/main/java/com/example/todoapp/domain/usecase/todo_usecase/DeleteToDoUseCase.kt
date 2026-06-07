package com.example.todoapp.domain.usecase.todo_usecase

import com.example.todoapp.domain.repositories.ToDoRepository

class DeleteToDoUseCase(
    private val toDoRepository: ToDoRepository
) {
    suspend operator fun invoke(
        toDoId : Int
    ) : String {
        return toDoRepository.deleteToDo(
            toDoId = toDoId
        )
    }
}