package com.example.todoapp.domain.usecase.local_user_manager_usecase

import com.example.todoapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class GetUserLogIn(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.getUserLoggedIn()
    }
}