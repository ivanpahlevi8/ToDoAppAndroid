package com.example.todoapp.core.di

import android.app.Application
import com.example.todoapp.data.manager.LocalUserManagerImpl
import com.example.todoapp.domain.manager.LocalUserManager
import com.example.todoapp.domain.usecase.local_user_manager_usecase.GetUserOnBoardUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserOnBoardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ECommerceAppModule {
    // provides local user manager
    @Provides
    @Singleton
    fun providesLocalUserManager(application: Application) : LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    // provides local user manager use case
    @Provides
    @Singleton
    fun providesLocalUserManagerUseCase(localUserManager: LocalUserManager) : LocalUserManagerUseCase {
        return LocalUserManagerUseCase(
            getUserOnBoardUseCase = GetUserOnBoardUseCase(
                localUserManager = localUserManager
            ),
            setUserOnBoardUseCase = SetUserOnBoardUseCase(
                localUserManager = localUserManager
            )
        )
    }
}