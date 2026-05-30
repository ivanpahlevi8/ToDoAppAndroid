package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ResponseDto
import retrofit2.http.POST
import retrofit2.http.PUT

interface ToDoRemoteAPI {
    @POST("api/ToDo/create-todo")
    suspend fun createToDo(createToDoDto: CreateToDoDto) : ResponseDto<String>

    @PUT("api/ToDo/update-todo")
    suspend fun updateToDo(createToDoDto: CreateToDoDto) : ResponseDto<String>
}