package com.example.todoapp.presentation.nv_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.main_navigation.MainNavigation

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
            // show main screen routes
            MainNavigation()
        }

        composable(
            route = Routes.OnBoardScreenRoutes.route
        ) {

        }
    }
}