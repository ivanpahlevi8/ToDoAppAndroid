package com.example.todoapp.presentation.authentication

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.value.Constants
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val localUserManagerUseCase: LocalUserManagerUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    // state for login
    private var _loginState by mutableStateOf<AuthState>(AuthState.IdleState)

    val loginState : State<AuthState> get() = derivedStateOf { _loginState }

    // state for register
    private var _registerState by mutableStateOf<AuthState>(AuthState.IdleState)

    val registerState : State<AuthState> get() = derivedStateOf { _registerState }

    fun onEvent(event : AuthEvent) {
        when(event) {
            is AuthEvent.OnLoginEvent -> {
                val loginUserDto = event.loginUserDto

                _loginState = AuthState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        val userModel = authUseCase.loginUserUseCase(
                            loginUserDto = loginUserDto
                        )

                        sharedPreferences.edit().putString(Constants.USER_ID, userModel.userId).apply()
                        sharedPreferences.edit().putString(Constants.EMAIL, userModel.userEmail).apply()
                        sharedPreferences.edit().putString(Constants.USER_NAME, userModel.userName).apply()
                        sharedPreferences.edit().putString(Constants.FIRST_NAME, userModel.userFirstName).apply()
                        sharedPreferences.edit().putString(Constants.LAST_NAME, userModel.userLastName).apply()

                        delay(600)

                        _loginState = AuthState.DataState(
                            userModel
                        )
                    } catch (e : Exception) {
                        val errMsg : String = "Error happen : ${e.message}"

                        _loginState = AuthState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is AuthEvent.OnRegisterEvent -> {
                val registerUserDto = event.registerUserDto

                _registerState = AuthState.LoadingState

                viewModelScope.launch {
                    try{
                        val userModel = authUseCase.registerUserUseCase(
                            registerUserDto = registerUserDto
                        )

                        delay(600)

                        _registerState = AuthState.DataState(
                            userModel
                        )
                    } catch (e : Exception) {
                        val errMsg : String = "Error happen : ${e.message}"

                        _registerState = AuthState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }
        }
    }

    fun updateRegisterState(newState : AuthState) {
        _registerState = newState
    }

    fun updateLoginState(newState : AuthState) {
        _loginState = newState
    }

    fun setUserLogin() {
        viewModelScope.launch {
            delay(500)

            localUserManagerUseCase.setUserLogInUseCase()
        }
    }
}