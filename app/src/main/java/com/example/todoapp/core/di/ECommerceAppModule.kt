package com.example.todoapp.core.di

import android.app.Application
import com.example.todoapp.core.value.Constants
import com.example.todoapp.data.manager.LocalUserManagerImpl
import com.example.todoapp.data.remote.AuthRemoteAPI
import com.example.todoapp.data.repositories.AuthRemoteRepositoryImpl
import com.example.todoapp.domain.manager.LocalUserManager
import com.example.todoapp.domain.repositories.AuthRemoteRepository
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.authorization_usecase.LoginUserUseCase
import com.example.todoapp.domain.usecase.authorization_usecase.RegisterUserUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.GetUserLogIn
import com.example.todoapp.domain.usecase.local_user_manager_usecase.GetUserOnBoardUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserLogInUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserLogOut
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserOnBoardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
            ),
            setUserLogInUseCase = SetUserLogInUseCase(
                localUserManager = localUserManager
            ),
            setUserLogOut = SetUserLogOut(
                localUserManager = localUserManager
            ),
            getUserLogIn = GetUserLogIn(
                localUserManager = localUserManager,
            )
        )
    }

    // provides auth remote api
    @Provides
    @Singleton
    fun providesAuthRemoteAPI() : AuthRemoteAPI{
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthRemoteAPI::class.java)
    }

    // provides auth remote repository
    @Provides
    @Singleton
    fun providesAuthRemoteRepository(
        authRemoteAPI: AuthRemoteAPI
    ) : AuthRemoteRepository{
        return AuthRemoteRepositoryImpl(
            authRemoteAPI = authRemoteAPI
        )
    }

    // provides auth remote use case
    @Provides
    @Singleton
    fun providesAuthRemoteUseCase(
        authRemoteRepository: AuthRemoteRepository,
    ) : AuthUseCase {
        return AuthUseCase(
            loginUserUseCase = LoginUserUseCase(
                authRemoteRepository = authRemoteRepository
            ),
            registerUserUseCase = RegisterUserUseCase(
                authRemoteRepository = authRemoteRepository,
            )
        )
    }
}