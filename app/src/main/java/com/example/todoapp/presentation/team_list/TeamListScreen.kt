@file:Suppress("UNCHECKED_CAST")

package com.example.todoapp.presentation.team_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.component.ErrorDialog
import com.example.todoapp.core.component.LoadingDialog
import com.example.todoapp.core.component.SuccessDialog
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateTeamDto
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.presentation.team_list.component.TeamItemList
import com.example.todoapp.presentation.team_list.component.TeamItemListShimmer

@Composable
fun TeamListScreen(
    state : TeamListState,
    createTeamState : TeamListState,
    userId : String,
    onEvent : (TeamListEvent) -> Unit,
    updateCreateTeamState : (TeamListState) -> Unit
){
    // create state for showing the create team dialog
    var showCreateTeamDialog by remember { mutableStateOf(false) }

    // create state for input
    var inputTeamName by remember { mutableStateOf("") }
    var inputTeamDescription by remember { mutableStateOf("") }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(
                        Dimension.SMALL_PADDING1
                    ),
                onClick = {
                    showCreateTeamDialog = true
                },
                shape = RoundedCornerShape(
                    16.dp
                ),
                containerColor = colorResource(
                    id = R.color.excellent_end
                )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_ic
                    ),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(22.dp),
                    tint = colorResource(
                        id = R.color.white
                    )
                )
            }
        }
    ) {
        it ->
        Column(
            modifier = Modifier
                .padding(
                    it
                )
        ) {
            when(state) {
                is TeamListState.DataState<*> -> {
                    val getData = state.data

                    TeamItemList(
                        itemList = getData as List<TeamModel>,
                        userId = userId
                    )
                }
                is TeamListState.ErrorState -> {
                    val errMsg = state.errMsg

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = errMsg,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W600
                            ),
                            color = colorResource(
                                id = R.color.error_color
                            ),
                            textAlign = TextAlign.Justify,
                        )
                    }
                }
                is TeamListState.LoadingState -> {
                    TeamItemListShimmer()
                }
                is TeamListState.IdleState -> {

                }
            }

            AnimatedVisibility(
                visible = showCreateTeamDialog
            ) {
                Dialog(
                    onDismissRequest = {
                        showCreateTeamDialog = !showCreateTeamDialog
                    }
                ) {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = colorResource(
                            id = R.color.card_information_background1
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimension.MEDIUM_PADDING1,
                                    vertical = Dimension.SMALL_PADDING2
                                ),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "Create Team",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W800,
                                    fontSize = 22.sp
                                ),
                                color = colorResource(
                                    id = R.color.text_title
                                )
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING1
                                    )
                            )

                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = inputTeamName,
                                onValueChange = {
                                    newValue : String -> inputTeamName = newValue
                                },
                                shape = MaterialTheme.shapes.medium,
                                label = {
                                    Text(
                                        text = "Team Name"
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text
                                )
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING1
                                    )
                            )

                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = inputTeamDescription,
                                onValueChange = {
                                    newValue : String -> inputTeamDescription = newValue
                                },
                                shape = MaterialTheme.shapes.medium,
                                label = {
                                    Text(
                                        text = "Team Description"
                                    )
                                },
                                singleLine = false,
                                maxLines = 3,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text
                                )
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING2
                                    )
                            )

                            Button(
                                onClick = {
                                    showCreateTeamDialog = !showCreateTeamDialog

                                    onEvent(
                                        TeamListEvent.OnAddTeam(
                                            CreateTeamDto(
                                                teamName = inputTeamName,
                                                teamDescription = inputTeamDescription,
                                                teamLeaderId = userId
                                            )
                                        )
                                    )

                                    inputTeamName = ""
                                    inputTeamDescription = ""
                                },
                                colors = ButtonColors(
                                    containerColor = colorResource(
                                        id = R.color.excellent_start
                                    ),
                                    disabledContentColor = colorResource(
                                        id = R.color.excellent_start
                                    ),
                                    contentColor = colorResource(
                                        id = R.color.white
                                    ),
                                    disabledContainerColor = colorResource(
                                        id = R.color.white
                                    )
                                )
                            ) {
                                Text(
                                    text = "CREATE",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W800,
                                        fontSize = 14.sp
                                    ),
                                    color = colorResource(
                                        id = R.color.white
                                    )
                                )
                            }
                        }
                    }
                }
            }

            when(createTeamState) {
                is TeamListState.DataState<*> -> {
                    // get data as a team model
                    val teamModel = createTeamState.data as TeamModel

                    SuccessDialog(
                        successTitle = "Success Create Team",
                        successMsg = "Team ${teamModel.teamName} successfully created, please add member is exist",
                        onDismiss = {
                            updateCreateTeamState(
                                TeamListState.IdleState
                            )
                        }
                    )
                }
                is TeamListState.ErrorState -> {
                    // get error
                    val errMsg = createTeamState.errMsg

                    ErrorDialog(
                        errMsg = errMsg,
                        onDismiss = {
                            updateCreateTeamState(
                                TeamListState.IdleState
                            )
                        }
                    )
                }
                is TeamListState.LoadingState -> {
                    LoadingDialog()
                }
                is TeamListState.IdleState -> {

                }
            }
        }
    }
}