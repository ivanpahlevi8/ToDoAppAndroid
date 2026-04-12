package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.dtos.SendConnectionRequestDto
import com.example.todoapp.domain.models.SendConnectionModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ConnectionRemoteAPI {
    // function to send connection
    @POST("api/Connection/send-connection")
    suspend fun sendUserConnection(
        @Body connectionRequestDto: SendConnectionRequestDto
    ) : ResponseDto<String>

    // function to accept connection
    @PUT("api/Connection/accept-connection")
    suspend fun acceptUserConnection(
        @Query("connectionId") connectionId : String
    ) : ResponseDto<SendConnectionModel>

    // get all connection
    @GET("api/Connection/get-all-connection")
    suspend fun getAllConnection(
        @Query("userId") userId : String
    ) : ResponseDto<List<SendConnectionModel>>

    // function to get requested connection
    @GET("api/Connection/get-request-connection")
    suspend fun getRequestConnection(
        @Query("requesterId") requesterId : String
    ) : ResponseDto<List<SendConnectionModel>>
}