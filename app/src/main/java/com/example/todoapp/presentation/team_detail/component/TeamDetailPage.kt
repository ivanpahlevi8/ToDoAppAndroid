package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.presentation.team_detail.TeamDetailEvent
import com.example.todoapp.presentation.team_detail.TeamDetailState
import com.example.todoapp.ui.theme.ToDoAppTheme
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeamDetailPage(
    teamModelState : TeamDetailState,
    teamRoleState : TeamDetailState,
    searchConnectionState : TeamDetailState,
    onEvent : (TeamDetailEvent) -> Unit,
){
    var showAddTeamRoleDialog by remember { mutableStateOf(false) }
    var showAddTeamMemberDialog by remember { mutableStateOf(false) }
    var showSelectRoleDialog by remember { mutableStateOf(false) }

    // for add team member data
    var selectedUserId by remember { mutableStateOf("") }
    var selectedTeamId by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimension.SMALL_PADDING1
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // team detail header
        when(teamModelState){
            is TeamDetailState.DataState<*> -> {
                val teamModel = teamModelState.data as TeamModel

                TeamDetailHeader(
                    teamModel = teamModel
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING3
                        )
                )

                TeamLeaderItem(
                    teamLead = teamModel.teamLeader ?: UserModel(
                        userFirstName = "Ivan",
                        userLastName = "Indirsyah",
                        userCreatedAt = "",
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "ivan.indirsya@gmail.com",
                        userPhoneNumber = "234325435"
                    )
                )

                // create team role dialog
                CreateTeamRoleDialog(
                    showDialog = showAddTeamRoleDialog,
                    onDismiss = {
                        showAddTeamRoleDialog = false
                    },
                    onAddRole = {
                            roleName : String ->
                        onEvent(
                            TeamDetailEvent.OnAddTeamRole(
                                teamRoleDto = TeamRoleDto(
                                    roleName = roleName,
                                    teamId = teamModel.teamId ?: 0,
                                )
                            )
                        )
                        showAddTeamRoleDialog = false
                    }
                )

                // add team member dialog
                AddTeamMemberDialog(
                    showDialog = showAddTeamMemberDialog,
                    onDismiss = {
                        showAddTeamMemberDialog = false
                    },
                    searchConnectionState = searchConnectionState,
                    onSearchConnection = {
                        searchName : String -> onEvent(
                            TeamDetailEvent.OnSearchConnection(
                                name = searchName,
                            )
                        )
                    },
                    onAddTeamMember = {
                        userId : String ->
                        // set selected user id
                        selectedUserId = userId
                        selectedTeamId = teamModel.teamId ?: 0

                        // unshow current dialog
                        showAddTeamMemberDialog = false

                        // show select dialog
                        showSelectRoleDialog = true;
                    }
                )
            }
            is TeamDetailState.ErrorState -> {

            }
            is TeamDetailState.LoadingState -> {
                TeamDetailHeaderShimmer()

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING3
                        )
                )

                TeamLeaderItemShimmer()
            }
            is TeamDetailState.IdleState -> {}
        }

        Spacer(
            modifier = Modifier
                .height(
                    Dimension.MEDIUM_PADDING3
                )
        )

        // team detail role list
        when(teamRoleState){
            is TeamDetailState.DataState<*> -> {
                val roleList = teamRoleState.data as List<TeamRoleDto>

                TeamRoleItemList(
                    roleList = roleList,
                    showTeamRoleDialog = {
                        Log.d("CHECK", "Add new team role button clicked")
                        showAddTeamRoleDialog = true
                    },
                    onDelete = {
                        teamRoleId : Int -> onEvent(
                            TeamDetailEvent.OnDeleteTeamRole(
                                teamRoleId = teamRoleId
                            )
                        )
                    }
                )

                // select role dialog
                SelectRoleDialog(
                    roleList = roleList,
                    showDialog = showSelectRoleDialog,
                    onSelectRole = {
                        roleId : Int ->
                        // unshow team role
                        showSelectRoleDialog = false

                        // assign user
                        onEvent(
                            TeamDetailEvent.OnAddTeamMember(
                                userId = selectedUserId,
                                teamId = selectedTeamId,
                                teamRole = roleId
                            )
                        )
                    },
                    onDismiss = {
                        showSelectRoleDialog = false;
                    }
                )
            }
            is TeamDetailState.ErrorState -> {

            }
            is TeamDetailState.LoadingState -> {
                TeamRoleItemListShimmer()
            }
            is TeamDetailState.IdleState -> {}
        }

        Spacer(
            modifier = Modifier
                .height(
                    Dimension.MEDIUM_PADDING3
                )
        )

        // team detail member list
        when(teamModelState){
            is TeamDetailState.DataState<*> -> {
                val teamModel = teamModelState.data as TeamModel

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    TeamMemberItemList(
                        teamMember = teamModel.teamUserMember ?: listOf(),
                        roleMember = teamModel.roleMember ?: listOf(),
                        onShowAddMemberDialog = {
                            showAddTeamMemberDialog = true
                        }
                    )
                }
            }
            is TeamDetailState.ErrorState -> {

            }
            is TeamDetailState.LoadingState -> {
                TeamMemberItemListShimmer()
            }
            is TeamDetailState.IdleState -> {}
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_TYPE_APPLIANCE)
@Composable
fun TeamDetailPagePreview() {
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
            TeamDetailPage(
                teamModelState = TeamDetailState.DataState(
                    data = TeamModel(
                        teamName = "Mobile Developer Team",
                        teamLeader = UserModel(
                            userFirstName = "Ivan",
                            userLastName = "Indirsyah",
                            userCreatedAt = "",
                            userId = "",
                            userName = "ivanpahlevi8",
                            userEmail = "ivan.indirsya@gmail.com",
                            userPhoneNumber = "234325435"
                        ),
                        createdAt = "2026-05-07T02:10:03.7602489+00:00",
                        teamUserMember = listOf(
                            UserModel(
                                userFirstName = "Ivan",
                                userLastName = "Indirsyah",
                                userCreatedAt = "",
                                userId = "",
                                userName = "",
                                userEmail = "ivan.indirsya@gmail.com",
                                userPhoneNumber = ""
                            ),
                            UserModel(
                                userFirstName = "Ivan",
                                userLastName = "Indirsyah",
                                userCreatedAt = "",
                                userId = "",
                                userName = "",
                                userEmail = "ivan.indirsya@gmail.com",
                                userPhoneNumber = ""
                            ),
                            UserModel(
                                userFirstName = "Ivan",
                                userLastName = "Indirsyah",
                                userCreatedAt = "",
                                userId = "",
                                userName = "",
                                userEmail = "ivan.indirsya@gmail.com",
                                userPhoneNumber = ""
                            )
                        ),
                        roleMember = listOf(
                            TeamRoleDto(
                                roleName = "Software Engineer",
                                teamId = 1
                            ),
                            TeamRoleDto(
                                roleName = "Software Engineer",
                                teamId = 1
                            ),
                            TeamRoleDto(
                                roleName = "Software Engineer",
                                teamId = 1
                            )
                        )
                    )
                ),
                teamRoleState = TeamDetailState.DataState(
                    data = listOf(
                        TeamRoleDto(
                            roleName = "Software Engineer",
                            teamId = 1
                        ),
                        TeamRoleDto(
                            roleName = "Software Engineer",
                            teamId = 1
                        ),
                        TeamRoleDto(
                            roleName = "Software Engineer",
                            teamId = 1
                        ),
                        TeamRoleDto(
                            roleName = "Software Engineer",
                            teamId = 1
                        ),
                    )
                ),
                onEvent = {},
                searchConnectionState = TeamDetailState.IdleState
            )
        }
    }
}