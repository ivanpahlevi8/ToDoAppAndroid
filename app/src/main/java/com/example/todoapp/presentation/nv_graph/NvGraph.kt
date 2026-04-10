package com.example.todoapp.presentation.nv_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.authentication.AuthenticationScreen
import com.example.todoapp.presentation.main_navigation.MainNavigation
import com.example.todoapp.presentation.main_navigation.MainNavigationViewModel
import com.example.todoapp.presentation.on_board_screen.OnBoardScreen
import com.example.todoapp.presentation.on_board_screen.OnBoardScreenState
import com.example.todoapp.presentation.on_board_screen.OnBoardScreenViewModel

@Composable
fun NvGraph(route : String) {
    // create nav controller
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = route,
    ) {
        composable(
            route = Routes.MainScreenRoutes.route
        ) {
            val mainNavigationViewModel : MainNavigationViewModel = hiltViewModel()

            // show main screen routes
            MainNavigation(
                onLogOut = {
                    mainNavigationViewModel.onLogout()
                }
            )
        }

        composable(
            route = Routes.AuthPageRoutes.route
        ) {
            AuthenticationScreen()
        }

        composable(
            route = Routes.OnBoardScreenRoutes.route
        ) {
            val onBoardScreenViewModel : OnBoardScreenViewModel = hiltViewModel()

            OnBoardScreen(
                onEvent = {
                    event -> onBoardScreenViewModel.onEvent(
                        event
                    )
                },
                state = onBoardScreenViewModel.onBoardState.value
            )
        }
    }
}