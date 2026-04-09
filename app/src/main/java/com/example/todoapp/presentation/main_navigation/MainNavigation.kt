package com.example.todoapp.presentation.main_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.presentation.main_navigation.component.NavBarItem
import com.example.todoapp.presentation.main_navigation.component.NavigationDrawer
import com.example.todoapp.presentation.nv_graph.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    // create nav bar item
    val navBarItemList : List<NavBarItem> = listOf(
        NavBarItem(
            title = "Movie Recommendation",
            icon = R.drawable.movie_icon,
        ),
    )

    // create nav controller
    val navController = rememberNavController()

    // get current route
    val getCurrentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    // get index selected route
    val indexSelectedRoute : Int = when(getCurrentRoute) {
        Routes.MovieRecommendationRoutes.route -> {0}
        else -> {0}
    }

    // create state for showing top app bar or not
    val showTopAppBar : Boolean = when(getCurrentRoute) {
        Routes.ListPostersDetailRoutes.route -> {
            false
        }
        else -> {
            true
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawer(
        itemList = navBarItemList,
        selectedItem = indexSelectedRoute,
        onClick = {
            index : Int ->
            when(index) {
                0 -> {
                    // close the drawer
                    scope.launch {
                        drawerState.apply {
                            close()
                        }
                    }

                    // navigate to destination tab
                    onMoveTab(
                        navController = navController,
                        route = Routes.MovieRecommendationRoutes.route
                    )
                }
                1 -> {
                    // close the drawer
                    scope.launch {
                        drawerState.apply {
                            close()
                        }
                    }

                    // navigate to destination tab
                    onMoveTab(
                        navController = navController,
                        route = Routes.SearchMovieCriticsRoutes.route
                    )
                }
            }
        },
        drawerState = drawerState,
        item = {
            Scaffold (
                topBar = { //TopBar to show title
                    if(showTopAppBar) {
                        TopAppBar(
                            title = {
                                when(indexSelectedRoute) {
                                    0 -> {
                                        Text(
                                            text = "Movie Recommendation List",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.W600,
                                                letterSpacing = 1.1.sp,
                                                fontSize = 20.sp,
                                            ),
                                            color = colorResource(
                                                id = R.color.text_title,
                                            )
                                        )
                                    }
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }) {
                                    Icon(  //Show Menu Icon on TopBar
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    }
                }
            ){
                val bottomPaddingValue = it.calculateBottomPadding()
                val topPaddingValue = it.calculateTopPadding()

                NavHost(
                    navController = navController,
                    startDestination = Routes.MovieRecommendationRoutes.route,
                    modifier = Modifier.padding(
                        top = topPaddingValue,
                        bottom = bottomPaddingValue
                    )
                ){
                    // create route for movie recommendation routes
                    composable(
                        route = Routes.MovieRecommendationRoutes.route
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                "Welcome to Home"
                            )
                        }
                    }
                }
            }
        }
    )
}

fun onMoveTab(
    navController: NavController,
    route : String
) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop= true
        restoreState= true
    }
}