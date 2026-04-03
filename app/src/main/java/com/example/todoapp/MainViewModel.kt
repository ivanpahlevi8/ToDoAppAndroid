package com.example.todoapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import com.example.todoapp.presentation.nv_graph.Routes
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(
    private val localUserManagerUseCase: LocalUserManagerUseCase
) : ViewModel() {
    // create initial route
    var initialRoute by mutableStateOf(Routes.OnBoardScreenRoutes.route)

    // create state to show splash screen
    var showSplashScreen by mutableStateOf(true)

    init {
        viewModelScope.launch {
            // add some delay for better transition on splash scren
            delay(500)

            // get flow of user on board status
            var getUserOnBoard : Flow<Boolean> = localUserManagerUseCase.getUserOnBoardUseCase()

            // collect flow
            getUserOnBoard
                .catch {
                    error ->
                    // create error message
                    var errorMsg : String = "Error happen : ${error.message}, ${error.stackTrace}"

                    // log the error
                    Log.e("Error", errorMsg)

                    // set route back to on board screen
                    initialRoute = Routes.OnBoardScreenRoutes.route
                }
                .collect{
                    isUserOnBoard : Boolean ->
                    // set route to landing route
                    initialRoute = Routes.MainScreenRoutes.route
                }

            // set show splash screen into false
            showSplashScreen = false
        }
    }
}