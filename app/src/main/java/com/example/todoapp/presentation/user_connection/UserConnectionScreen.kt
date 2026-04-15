@file:Suppress("UNCHECKED_CAST")

package com.example.todoapp.presentation.user_connection

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserConnectionModel
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.presentation.user_connection.component.ConnectionUserPage
import com.example.todoapp.presentation.user_connection.component.ConnectionUserPageShimmer
import com.example.todoapp.presentation.user_connection.component.RequestConnectionUserPage
import com.example.todoapp.presentation.user_connection.component.RequestConnectionUserPageShimmer
import com.example.todoapp.ui.theme.ToDoAppTheme

enum class ConnectionTab {
    REQUESTS, CURRENT
}

@Composable
fun UserConnectionScreen(
    requestConnectionState : UserConnectionState,
    currentConnection : UserConnectionState,
    onEvent : (UserConnectionEvent) -> Unit,
){
    var showRequestConnection by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    60.dp
                )
                .shadow(
                    elevation = 2.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if(showRequestConnection){
                                colorResource(
                                    id = R.color.tab_active_bg
                                )
                            } else {
                                colorResource(
                                    id = R.color.tab_container_bg
                                )
                            }
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(), //
                        ) {
                            if(!showRequestConnection){
                                onEvent(
                                    UserConnectionEvent.OnGetRequestConnection
                                )
                            }

                            showRequestConnection = true
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Request Connection",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W900,
                                fontSize = 18.sp
                            ),
                            color = if(showRequestConnection) {
                                colorResource(
                                    id = R.color.tab_active_text
                                )
                            } else {
                                colorResource(
                                    id = R.color.tab_inactive_text
                                )
                            }
                        )
                    }

                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier
                            .align(
                                alignment = Alignment.BottomCenter
                            ),
                        visible = showRequestConnection,
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                        exit = fadeOut()
                    ) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 2.dp,
                                    bottom = 2.dp
                                ),
                            thickness = 4.dp,
                            color = colorResource(id = R.color.tab_indicator_tint)
                        )
                    }
                }

                VerticalDivider(
                    modifier = Modifier
                        .padding(
                            vertical = 3.dp
                        )
                )

                Box(
                    modifier = Modifier
                        .weight(
                            1f
                        )
                        .background(
                            color = if(!showRequestConnection){
                                colorResource(
                                    id = R.color.tab_active_bg
                                )
                            } else {
                                colorResource(
                                    id = R.color.tab_container_bg
                                )
                            }
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(), //
                        ) {
                            if(showRequestConnection){
                                onEvent(
                                    UserConnectionEvent.OnGetUserConnection
                                )
                            }

                            showRequestConnection = false
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Current Connection",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W900,
                                fontSize = 18.sp
                            ),
                            color = if(!showRequestConnection) {
                                colorResource(
                                    id = R.color.tab_active_text
                                )
                            } else {
                                colorResource(
                                    id = R.color.tab_inactive_text
                                )
                            }
                        )
                    }

                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier
                            .align(
                                alignment = Alignment.BottomCenter
                            ),
                        visible = !showRequestConnection,
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                        exit = fadeOut()
                    ) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    end = 2.dp,
                                    bottom = 2.dp
                                ),
                            thickness = 4.dp,
                            color = colorResource(id = R.color.tab_indicator_tint)
                        )
                    }
                }
            }
        }

        /** Page start here **/
        AnimatedContent(
            targetState = showRequestConnection,
            transitionSpec = {
                // Determine direction based on where we are coming from and going to
                if (showRequestConnection) {
                    (slideInHorizontally(animationSpec = tween(300)) { width -> -width } + fadeIn(tween(300))).togetherWith(
                        slideOutHorizontally(animationSpec = tween(300)) { width -> width } + fadeOut(tween(300))
                    )
                } else {
                    (slideInHorizontally(animationSpec = tween(300)) { width -> width } + fadeIn(tween(300))).togetherWith(
                        slideOutHorizontally(animationSpec = tween(300)) { width -> -width } + fadeOut(tween(300))
                    )
                }
            },
            label = "tab_transition" // Useful for Android Studio animation inspection
        ) { currentTab ->

            // Render the correct screen based on the current animated state
            when (currentTab) {
                true -> {
                    when(
                        requestConnectionState
                    ) {
                        is UserConnectionState.DataState<*> -> {
                            val getData = requestConnectionState.data

                            RequestConnectionUserPage(
                                connectionUserItems = getData as List<UserConnectionModel>
                            )
                        }

                        is UserConnectionState.ErrorState -> {
                            val errMsg = requestConnectionState.errMsg

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = errMsg,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W800,
                                        fontSize = 18.sp
                                    ),
                                    color = colorResource(
                                        id = R.color.error_color
                                    )
                                )
                            }
                        }

                        is UserConnectionState.LoadingState -> {
                            RequestConnectionUserPageShimmer()
                        }
                        is UserConnectionState.IdleState -> {

                        }
                    }
                }
                false -> {
                    when(
                        currentConnection
                    ) {
                        is UserConnectionState.DataState<*> -> {
                            val getData = currentConnection.data

                            ConnectionUserPage(
                                userItems = getData as List<UserConnectionModel>
                            )
                        }

                        is UserConnectionState.ErrorState -> {
                            val errMsg = currentConnection.errMsg

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = errMsg,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W800,
                                        fontSize = 18.sp
                                    ),
                                    color = colorResource(
                                        id = R.color.error_color
                                    )
                                )
                            }
                        }

                        is UserConnectionState.LoadingState -> {
                            ConnectionUserPageShimmer()
                        }
                        is UserConnectionState.IdleState -> {

                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserConnectionScreenPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.SMALL_PADDING2
                )
        ) {
            UserConnectionScreen(
                requestConnectionState = UserConnectionState.IdleState,
                currentConnection = UserConnectionState.IdleState,
                onEvent = {}
            )
        }
    }
}