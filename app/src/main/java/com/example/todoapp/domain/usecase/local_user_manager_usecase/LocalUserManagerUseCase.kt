package com.example.todoapp.domain.usecase.local_user_manager_usecase

data class LocalUserManagerUseCase(
    val getUserOnBoardUseCase: GetUserOnBoardUseCase,
    val setUserOnBoardUseCase: SetUserOnBoardUseCase,
    val setUserLogInUseCase: SetUserLogInUseCase,
    val setUserLogOut: SetUserLogOut,
    val getUserLogIn: GetUserLogIn
)
