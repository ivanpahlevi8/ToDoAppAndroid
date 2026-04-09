package com.example.todoapp.presentation.authentication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.authentication.component.LoginPage
import com.example.todoapp.presentation.authentication.component.RegisterPage
import com.example.todoapp.presentation.nv_graph.Routes

@Composable
fun AuthenticationScreen(){
    // create nav controller
    val navController = rememberNavController()

    // create auth view mode
    val authViewModel : AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginPageRoutes.route,
    ) {
        composable(
            route = Routes.LoginPageRoutes.route
        ) {
            // show main screen routes
            LoginPage(
                onRegister = {
                    navController.navigate(
                        Routes.RegisterPageRoutes.route
                    )
                },
                onEvent = {
                    event -> authViewModel.onEvent(
                        event = event
                    )
                },
                loginState = authViewModel.loginState.value,
                onUpdateLoginState = {
                    newState -> authViewModel.updateLoginState(
                        newState
                    )
                },
                setUserLogIn = {
                    authViewModel.setUserLogin()
                }
            )
        }

        composable(
            route = Routes.RegisterPageRoutes.route
        ) {
            RegisterPage(
                onCancel = {
                    navController.popBackStack()
                },
                onEvent = {
                    event -> authViewModel.onEvent(
                        event = event
                    )
                },
                registerState = authViewModel.registerState.value,
                updateRegisterState = {
                    newState -> authViewModel.updateRegisterState(
                        newState
                    )
                }
            )
        }
    }
}