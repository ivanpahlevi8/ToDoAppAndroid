package com.example.todoapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    // create function to set user onboard
    suspend fun setUserOnBoard()
    fun getUserOnBoard() : Flow<Boolean>
}