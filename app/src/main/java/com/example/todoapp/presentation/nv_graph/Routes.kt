package com.example.todoapp.presentation.nv_graph

sealed class Routes(
    val route : String,
) {
    object OnBoardScreenRoutes : Routes(route = "onboard-screen-routes")
    object MainScreenRoutes : Routes(route = "main-screen-routes")

    object MovieRecommendationRoutes : Routes(route = "movie-recommendation-routes")
    object SearchMovieCriticsRoutes : Routes(route = "search-movie-critics-routes")
    object SearchTvShowsRoutes : Routes(route = "search-tv-shows-routes")
    object ShowSoonTheaterRoutes : Routes(route = "show-soon-theater-routes")
    object SearchPostersRoutes : Routes(route = "search-posters-route")
    object ListPostersDetailRoutes : Routes(route = "list-posters-detail-route")

    object reviewMovieCriticsLocalRoutes : Routes(route = "review-movie-critics-local-route")
    object BookmarkMovieRecommendationRoutes : Routes(route = "bookmark-movie-recommendation-route")
}