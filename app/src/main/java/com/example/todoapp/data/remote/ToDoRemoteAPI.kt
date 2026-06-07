package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ToDoRemoteAPI {
    @POST("api/ToDo/create-todo")
    suspend fun createToDo(@Body createToDoDto: CreateToDoDto) : ResponseDto<CreateToDoDto>

    @PUT("api/ToDo/update-todo")
    suspend fun updateToDo(@Body createToDoDto: CreateToDoDto) : ResponseDto<String>

    @GET("api/ToDo/get-todo-project")
    suspend fun getToDoProject(@Query("projectId") projectId : Int) : ResponseDto<List<CreateToDoDto>>

    @DELETE("api/ToDo/delete-todo")
    suspend fun deleteToDo(@Query("toDoId") toDoId : Int) : ResponseDto<String>
}