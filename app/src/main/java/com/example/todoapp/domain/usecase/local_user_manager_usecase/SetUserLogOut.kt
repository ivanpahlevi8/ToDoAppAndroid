package com.example.todoapp.domain.usecase.local_user_manager_usecase

import com.example.todoapp.domain.manager.LocalUserManager

class SetUserLogOut(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.setUserLogOut()
    }
}