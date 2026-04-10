package com.example.todoapp.presentation.nv_graph

sealed class Routes(
    val route : String,
) {
    object OnBoardScreenRoutes : Routes(route = "onboard-screen-routes")
    object MainScreenRoutes : Routes(route = "main-screen-routes")

    object MovieRecommendationRoutes : Routes(route = "movie-recommendation-routes")
    object SearchMovieCriticsRoutes : Routes(route = "search-movie-critics-routes")
    object ListPostersDetailRoutes : Routes(route = "list-posters-detail-route")

    object AuthPageRoutes : Routes(route = "auth-page-route")
    object LoginPageRoutes : Routes(route = "login-page-route")
    object RegisterPageRoutes : Routes(route = "register-page-route")

    object SearchFriendRoutes : Routes(route = "search-friend-route")
}