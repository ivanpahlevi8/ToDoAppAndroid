package com.example.todoapp.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.todoapp.core.value.Constants
import com.example.todoapp.data.manager.LocalUserManagerImpl
import com.example.todoapp.data.remote.AuthRemoteAPI
import com.example.todoapp.data.remote.ConnectionRemoteAPI
import com.example.todoapp.data.repositories.AuthRemoteRepositoryImpl
import com.example.todoapp.data.repositories.ConnectionRemoteRepositoryImpl
import com.example.todoapp.domain.manager.LocalUserManager
import com.example.todoapp.domain.repositories.AuthRemoteRepository
import com.example.todoapp.domain.repositories.ConnectionRemoteRepository
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.authorization_usecase.GetUserIdUseCase
import com.example.todoapp.domain.usecase.authorization_usecase.GetUserUseCase
import com.example.todoapp.domain.usecase.authorization_usecase.LoginUserUseCase
import com.example.todoapp.domain.usecase.authorization_usecase.RegisterUserUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.GetUserLogIn
import com.example.todoapp.domain.usecase.local_user_manager_usecase.GetUserOnBoardUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserLogInUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserLogOut
import com.example.todoapp.domain.usecase.local_user_manager_usecase.SetUserOnBoardUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.AcceptUserConnectionUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.DeclineUserUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.GetAllConnectionUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.GetConnectionDisconnectToUserUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.GetConnectionDisconnectedByUserUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.GetConnectionRejectToUserUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.GetConnectionRejectedByUserUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.GetRequestConnectionUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.SendUserConnectionUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.UnConnectUserUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.UserConnectionUseCase
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
    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application) : SharedPreferences {
        return application.getSharedPreferences(Constants.AUTH_PREFERENCES, Context.MODE_PRIVATE)
    }

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
            ),
            getUserUseCase = GetUserUseCase(
                authRemoteRepository = authRemoteRepository,
            ),
            getUserIdUseCase = GetUserIdUseCase(
                authRemoteRepository = authRemoteRepository
            )
        )
    }

    // provides connection remote api
    @Provides
    @Singleton
    fun providesConnectionRemoteAPI() : ConnectionRemoteAPI {
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
            .create(ConnectionRemoteAPI::class.java)
    }

    // provides connection remote repository
    @Provides
    @Singleton
    fun providesConnectionRemoteRepository(
        connectionRemoteAPI: ConnectionRemoteAPI
    ) : ConnectionRemoteRepository {
        return ConnectionRemoteRepositoryImpl(
            connectionRemoteAPI = connectionRemoteAPI
        )
    }

    // provides connection remote use case
    @Provides
    @Singleton
    fun providesConnectionRemoteUseCase(
        connectionRemoteRepository: ConnectionRemoteRepository
    ) : UserConnectionUseCase{
        return UserConnectionUseCase(
            sendUserConnectionUseCase = SendUserConnectionUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            acceptUserConnectionUseCase = AcceptUserConnectionUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            getRequestConnectionUseCase = GetRequestConnectionUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            getAllConnectionUseCase = GetAllConnectionUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            unConnectedUserUseCase = UnConnectUserUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            declineUserUseCase = DeclineUserUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            getConnectionRejectedByUserUseCase = GetConnectionRejectedByUserUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            getConnectionRejectToUserUseCase = GetConnectionRejectToUserUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            getConnectionDisconnectedByUserUseCase = GetConnectionDisconnectedByUserUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            ),
            getConnectionDisconnectToUserUseCase = GetConnectionDisconnectToUserUseCase(
                connectionRemoteRepository = connectionRemoteRepository
            )
        )
    }
}