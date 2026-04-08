package com.example.todoapp.presentation.authentication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.authentication.component.LoginPage
import com.example.todoapp.presentation.authentication.component.RegisterPage
import com.example.todoapp.presentation.main_navigation.MainNavigation
import com.example.todoapp.presentation.nv_graph.Routes
import com.example.todoapp.presentation.on_board_screen.OnBoardScreen
import com.example.todoapp.presentation.on_board_screen.OnBoardScreenViewModel

@Composable
fun AuthenticationScreen(){
    // create nav controller
    val navController = rememberNavController()

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
                }
            )
        }

        composable(
            route = Routes.RegisterPageRoutes.route
        ) {
            RegisterPage(
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}