package com.example.todoapp.domain.usecase.user_connection_usecase

data class UserConnectionUseCase(
    val sendUserConnectionUseCase: SendUserConnectionUseCase,
    val acceptUserConnectionUseCase: AcceptUserConnectionUseCase,
    val getRequestConnectionUseCase: GetRequestConnectionUseCase,
    val getAllConnectionUseCase: GetAllConnectionUseCase,
    val unConnectedUserUseCase : UnConnectUserUseCase,
    val declineUserUseCase: DeclineUserUseCase,
    val getConnectionRejectedByUserUseCase: GetConnectionRejectedByUserUseCase,
    val getConnectionRejectToUserUseCase: GetConnectionRejectToUserUseCase,
    val getConnectionDisconnectedByUserUseCase: GetConnectionDisconnectedByUserUseCase,
    val getConnectionDisconnectToUserUseCase: GetConnectionDisconnectToUserUseCase
)
