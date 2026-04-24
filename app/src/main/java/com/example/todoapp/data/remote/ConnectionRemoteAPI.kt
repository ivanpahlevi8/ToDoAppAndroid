package com.example.todoapp.data.remote

import com.example.todoapp.data.dtos.ResponseDto
import com.example.todoapp.data.dtos.SendConnectionRequestDto
import com.example.todoapp.domain.models.SendConnectionModel
import retrofit2.http.Body
import retrofit2.http.DELETE
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
    @GET("api/Connection/get-requested-connection")
    suspend fun getRequestConnection(
        @Query("requesterId") requesterId : String
    ) : ResponseDto<List<SendConnectionModel>>

    // function to un connect user
    @PUT("api/Connection/unconnect-user")
    suspend fun unConnectUser(
        @Query("connectionId") connectionId : String
    ) : ResponseDto<SendConnectionModel>

    // function to decline connection
    @PUT("api/Connection/declined-connection")
    suspend fun declineConnectionUser(
        @Query("connectionId") connectionId: String
    ) : ResponseDto<SendConnectionModel>

    // function to get connection rejected by user
    @GET("api/Connection/connection-rejected-byuser")
    suspend fun getConnectionRejectedByUser(
        @Query("userId") userId: String
    ) : ResponseDto<List<SendConnectionModel>>

    // function to get connection reject to user
    @GET("api/Connection/connection-reject-touser")
    suspend fun getConnectionRejectToUser(
        @Query("userId") userId : String
    ) : ResponseDto<List<SendConnectionModel>>

    // function to get connection disconnect by user
    @GET("api/Connection/connection-disconnected-byuser")
    suspend fun getConnectionDisconnectByUser(
        @Query("userId") userId: String
    ) : ResponseDto<List<SendConnectionModel>>

    // function to get connection disconnect to user
    @GET("api/Connection/connection-disconnect-touser")
    suspend fun getConnectionDisconnectToUser(
        @Query("userId") userId: String
    ) : ResponseDto<List<SendConnectionModel>>

    // function to delete connection
    @DELETE("api/Connection/remove-connection")
    suspend fun deleteConnection(@Query("connectionId") connectionId: String) : ResponseDto<SendConnectionModel>

    // function to get requested user connection
    @GET("api/Connection/get-reques-connection")
    suspend fun getRequestConnectionToUser(@Query("userId") userId: String) : ResponseDto<List<SendConnectionModel>>

    // function to get is connected status
    @GET("api/Connection/get-is-connected")
    suspend fun getIsConnectedStatus(
        @Query("userId") userId : String,
        @Query("userConnectedId") userConnectedId : String
    ) : ResponseDto<Boolean>
}