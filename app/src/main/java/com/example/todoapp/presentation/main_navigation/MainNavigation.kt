package com.example.todoapp.presentation.main_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.R
import com.example.todoapp.presentation.main_navigation.component.NavBarItem
import com.example.todoapp.presentation.main_navigation.component.NavigationDrawer
import com.example.todoapp.presentation.nv_graph.Routes
import com.example.todoapp.presentation.search_friend.SearchFriendScreen
import com.example.todoapp.presentation.search_friend.SearchFriendViewModel
import com.example.todoapp.presentation.team_detail.TeamDetailEvent
import com.example.todoapp.presentation.team_detail.TeamDetailScreen
import com.example.todoapp.presentation.team_detail.TeamDetailViewModel
import com.example.todoapp.presentation.team_list.TeamListScreen
import com.example.todoapp.presentation.team_list.TeamListViewModel
import com.example.todoapp.presentation.team_project.TeamProjectScreen
import com.example.todoapp.presentation.team_project.TeamProjectViewModel
import com.example.todoapp.presentation.project_to_do.ToDoScreen
import com.example.todoapp.presentation.project_to_do.ToDoViewModel
import com.example.todoapp.presentation.user_connection.UserConnectionScreen
import com.example.todoapp.presentation.user_connection.UserConnectionViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    onLogOut : () -> Unit,
) {
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
    val indexSelectedRoute: Int = when {
        getCurrentRoute == Routes.MovieRecommendationRoutes.route -> 0
        getCurrentRoute == Routes.SearchFriendRoutes.route -> 1
        getCurrentRoute == Routes.UserConnectionRoutes.route -> 2
        getCurrentRoute == Routes.TeamListRoutes.route -> 3
        (getCurrentRoute?.startsWith(Routes.TeamDetailRoutes.route)) ?: false -> 4
        (getCurrentRoute?.startsWith(Routes.ProjectByTeamRoutes.route)) ?: false -> 5
        (getCurrentRoute?.startsWith(Routes.ProjectToDoRoutes.route)) ?: false -> 6
        else -> 0
    }

    // create state for showing top app bar or not
    val showTopAppBar : Boolean = when(getCurrentRoute) {
        Routes.ListPostersDetailRoutes.route -> {
            false
        }
        Routes.SearchFriendRoutes.route -> {
            false
        }
        else -> {
            true
        }
    }

    // create state for showing floating action button
    val showFloatingButton : Boolean = when(getCurrentRoute) {
        Routes.TeamListRoutes.route -> {
            true
        }
        else -> {
            false
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
                                    4 -> {
                                        Text(
                                            text = "Team Detail",
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
                                    5 -> {
                                        Text(
                                            text = "Team Projects",
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
                                    6 -> {
                                        Text(
                                            text = "Projects Detail",
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
                                when(indexSelectedRoute) {
                                    0 -> {
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
                                    2 -> {
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(  //Show Menu Icon on TopBar
                                                painter = painterResource(
                                                    id = R.drawable.arrow_back_ic
                                                ),
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                    3 -> {
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(  //Show Menu Icon on TopBar
                                                painter = painterResource(
                                                    id = R.drawable.arrow_back_ic
                                                ),
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                    4 -> {
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(  //Show Menu Icon on TopBar
                                                painter = painterResource(
                                                    id = R.drawable.arrow_back_ic
                                                ),
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                    5,6 -> {
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(  //Show Menu Icon on TopBar
                                                painter = painterResource(
                                                    id = R.drawable.arrow_back_ic
                                                ),
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                }
                            },
                            actions = {
                                when(indexSelectedRoute){
                                    4 -> {
                                        IconButton(
                                            onClick = {
                                                val currentEntry = navController.currentBackStackEntry

                                                val teamIdString = currentEntry?.arguments?.getString("teamId") ?: "0"

                                                // safely convert to Int
                                                val teamId = teamIdString.toIntOrNull() ?: 0

                                                // navigate to detail
                                                navController.navigate(
                                                    Routes.ProjectByTeamRoutes.route + "/$teamId"
                                                )
                                            }
                                        ) {
                                            Icon(
                                                painter = painterResource(
                                                    id = R.drawable.assignment_ic
                                                ),
                                                modifier = Modifier
                                                    .size(20.dp),
                                                tint = colorResource(
                                                    id = R.color.text_title
                                                ),
                                                contentDescription = "Assignment Icon"
                                            )
                                        }
                                    }
                                    5,6 -> {}
                                    else -> {
                                        IconButton(
                                            onClick = {
                                                navController.navigate(
                                                    Routes.UserConnectionRoutes.route
                                                )
                                            }
                                        ) {
                                            Icon(
                                                painter = painterResource(
                                                    id = R.drawable.people_alt_ic
                                                ),
                                                modifier = Modifier
                                                    .size(20.dp),
                                                tint = colorResource(
                                                    id = R.color.text_title
                                                ),
                                                contentDescription = "People Icon"
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                },
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

                    // route for search friend
                    composable(
                        route = Routes.SearchFriendRoutes.route
                    ) {
                        val searchFriendViewModel : SearchFriendViewModel = hiltViewModel()

                        SearchFriendScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                            onEvent = {
                                event -> searchFriendViewModel.onEvent(
                                    event
                                )
                            },
                            searchState = searchFriendViewModel.searchFriendState.value,
                            addFriendState = searchFriendViewModel.addFriendState.value,
                            updateAddFriendState = {
                                newState -> searchFriendViewModel.updateAddFriendState(
                                    newState = newState
                                )
                            },
                            updateSearchFriendState = {
                                newState -> searchFriendViewModel.updateSearchFriendState(
                                    newState = newState
                                )
                            }
                        )
                    }

                    // route for connection user
                    composable(
                        route = Routes.UserConnectionRoutes.route
                    ) {
                        val userConnectionViewModel : UserConnectionViewModel = hiltViewModel()

                        UserConnectionScreen(
                            requestConnectionState = userConnectionViewModel.requestConnectionState.value,
                            currentConnection = userConnectionViewModel.connectedUserState.value,
                            onEvent = {
                                event -> userConnectionViewModel.onEvent(
                                    event = event
                                )
                            },
                            acceptRequestState = userConnectionViewModel.acceptConnectionState.value,
                            updateAcceptConnectionState = {
                                newState -> userConnectionViewModel.updateAcceptConnectionState(
                                    newState = newState
                                )
                            },
                            disconnectionState = userConnectionViewModel.disconnectConnectionState.value,
                            updateDisconnectConnectionState = {
                                newState -> userConnectionViewModel.updateDisconnectConnectionState(
                                    newState = newState
                                )
                            },
                            declineState = userConnectionViewModel.declineConnectionState.value,
                            updateDeclineConnectionState = {
                                newState -> userConnectionViewModel.updateDeclineConnectionState(
                                    newState = newState
                                )
                            },
                            unFollowState = userConnectionViewModel.unFollowConnectionState.value,
                            updateUnFollowState = {
                                newState -> userConnectionViewModel.updateUnFollowConnectionState(
                                    newState = newState
                                )
                            }
                        )
                    }

                    // route for team list
                    composable(
                        route = Routes.TeamListRoutes.route
                    ) {
                        val teamListViewMode : TeamListViewModel = hiltViewModel()

                        TeamListScreen(
                            state = teamListViewMode.listTeamState.value,
                            userId = teamListViewMode.userId,
                            onEvent = {
                                event -> teamListViewMode.onEvent(
                                    event = event
                                )
                            },
                            createTeamState = teamListViewMode.createTeamState.value,
                            updateCreateTeamState = {
                                newState -> teamListViewMode.updateCreateTeamState(
                                    newState = newState
                                )
                            },
                            onTeamDetail = {
                                teamId : Int -> navController.navigate(
                                    Routes.TeamDetailRoutes.route + "/$teamId"
                                )
                            }
                        )
                    }

                    // route for team detail
                    composable(
                        route = Routes.TeamDetailRoutes.route + "/{teamId}",
                        arguments = listOf(navArgument("teamId"){type= NavType.StringType})
                    ){
                        val teamDetailViewModel : TeamDetailViewModel = hiltViewModel()

                        TeamDetailScreen(
                            state = teamDetailViewModel.teamDetailState.value,
                            teamRoleListState = teamDetailViewModel.roleOnTeamState.value,
                            onEvent = {
                                event : TeamDetailEvent -> teamDetailViewModel.onEvent(
                                    event = event
                                )
                            },
                            createTeamRoleState = teamDetailViewModel.createTeamRoleState.value,
                            updateCreateTeamRoleState = {
                                newState -> teamDetailViewModel.updateCreateTeamRoleState(
                                    newState = newState
                                )
                            },
                            deleteTeamRoleState = teamDetailViewModel.deleteTeamRoleState.value,
                            updateDeleteTeamRoleState = {
                                newState -> teamDetailViewModel.updateDeleteTeamRoleState(
                                    newState = newState
                                )
                            },
                            searchConnectionState = teamDetailViewModel.searchConnectionState.value,
                            addTeamMemberState = teamDetailViewModel.addTeamMemberState.value,
                            updateAddTeamMemberState = {
                                newState -> teamDetailViewModel.updateAddTeamMemberState(
                                    newState
                                )
                            },
                            removeTeamMemberState = teamDetailViewModel.removeTeamMemberState.value,
                            updateRemoveTeamMemberState = {
                                newState -> teamDetailViewModel.updateRemoveTeamMemberState(
                                    newState = newState
                                )
                            },
                            isTeamLeader = teamDetailViewModel.isTeamLeader,
                            loginUserId = teamDetailViewModel.loginUserId,
                        )
                    }

                    // route for team project
                    composable(
                        route = Routes.ProjectByTeamRoutes.route + "/{teamId}",
                        arguments = listOf(navArgument("teamId"){type= NavType.StringType})
                    ) {
                        val projectTeamViewModel : TeamProjectViewModel = hiltViewModel()

                        TeamProjectScreen(
                            teamProjectState = projectTeamViewModel.getProjectTeamState.value,
                            onProjectDetail = {
                                projectId -> navController.navigate(
                                    Routes.ProjectToDoRoutes.route + "/$projectId"
                                )
                            },
                            onEvent = {
                                event -> projectTeamViewModel.onEvent(
                                    event = event
                                )
                            },
                            createTeamProjectState = projectTeamViewModel.createProjectTeam.value,
                            updateTeamProjectState = {
                                newState -> projectTeamViewModel.updateCreateProjectTeamState(
                                    newState
                                )
                            }
                        )
                    }

                    // route for project to do
                    composable(
                        route = Routes.ProjectToDoRoutes.route + "/{projectId}",
                        arguments = listOf(navArgument("projectId"){type= NavType.StringType})
                    ) {
                        val projectToDoViewMode : ToDoViewModel = hiltViewModel()

                        val grabbedToDoList by projectToDoViewMode.grabbedToDoItem.collectAsState()

                        val createToDo by projectToDoViewMode.createdToDo.collectAsState()
                        val processedToDo by projectToDoViewMode.processedToDo.collectAsState()
                        val finishedToDo by projectToDoViewMode.finishedToDo.collectAsState()

                        ToDoScreen(
                            grabbedToDoList = grabbedToDoList,
                            createdToDoList = createToDo,
                            processedToDoList = processedToDo,
                            finishedToDoList = finishedToDo,
                            onEvent = {
                                event -> projectToDoViewMode.onEvent(
                                    event
                                )
                            },
                            updateToDoPosition = {
                                toDoPointer -> projectToDoViewMode.updateToDoPosition(
                                    toDoPointer
                                )
                            },
                            deleteToDoPosition = {
                                toDoPointer -> projectToDoViewMode.deleteToDoPosition(
                                    toDoPointer = toDoPointer
                                )
                            },
                            addToDoState = projectToDoViewMode.createToDoState.value,
                            updateToDoState = projectToDoViewMode.updateToDoItemState.value,
                            updateAddToDoState = {
                                newState -> projectToDoViewMode.updateAddToDoState(newState)
                            },
                            updateUpdateToDoState = {
                                newState -> projectToDoViewMode.updateUpdateToDoState(
                                    newState
                                )
                            },
                            projectDetailState = projectToDoViewMode.projectDetailState.value,
                            getAllToDoState = projectToDoViewMode.getAllToDoState.value,
                            deleteToDoState = projectToDoViewMode.deleteToDoState.value
                        )
                    }
                }
            }
        },
        onProfile = {},
        onLogout = {
            onLogOut()
        },
        onFriendsPage = {
            navController.navigate(
                Routes.SearchFriendRoutes.route
            )

            scope.launch {
                drawerState.apply {
                    close()
                }
            }
        },
        onTeamPage = {
            navController.navigate(
                Routes.TeamListRoutes.route
            )

            scope.launch {
                drawerState.apply {
                    close()
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