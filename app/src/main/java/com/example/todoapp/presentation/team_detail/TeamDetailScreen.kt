package com.example.todoapp.presentation.team_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.todoapp.core.component.ErrorDialog
import com.example.todoapp.core.component.LoadingDialog
import com.example.todoapp.core.component.SuccessDialog
import com.example.todoapp.data.dtos.AssignUserDto
import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.presentation.team_detail.component.TeamDetailPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeamDetailScreen(
    state : TeamDetailState,
    teamRoleListState : TeamDetailState,
    createTeamRoleState : TeamDetailState,
    deleteTeamRoleState : TeamDetailState,
    searchConnectionState : TeamDetailState,
    addTeamMemberState : TeamDetailState,
    updateCreateTeamRoleState : (TeamDetailState) -> Unit,
    updateDeleteTeamRoleState : (TeamDetailState) -> Unit,
    updateAddTeamMemberState : (TeamDetailState) -> Unit,
    onEvent: (TeamDetailEvent) -> Unit,
) {
    TeamDetailPage(
        teamModelState = state,
        teamRoleState = teamRoleListState,
        searchConnectionState = searchConnectionState,
        onEvent = onEvent
    )

    // check state for create team
    when(createTeamRoleState) {
        is TeamDetailState.DataState<*> -> {
            // get data
            val getData = createTeamRoleState.data as TeamRoleDto

            // show success dialog
            SuccessDialog(
                successTitle = "Success Create Role",
                successMsg = "Role with role name ${getData.roleName} has been created",
                onDismiss = {
                    updateCreateTeamRoleState(TeamDetailState.IdleState)
                }
            )
        }
        is TeamDetailState.ErrorState -> {
            // get error message
            val errMsg = createTeamRoleState.errMsg

            // show error message
            ErrorDialog(
                errMsg = errMsg,
                onDismiss = {
                    updateCreateTeamRoleState(TeamDetailState.IdleState)
                }
            )
        }
        is TeamDetailState.LoadingState -> {
            LoadingDialog()
        }
        is TeamDetailState.IdleState -> {

        }
    }

    // check state for delete team role
    when(deleteTeamRoleState) {
        is TeamDetailState.DataState<*> -> {
            // get data
            val getData = deleteTeamRoleState.data as String

            // show success message
            SuccessDialog(
                successTitle = "Success",
                successMsg = "Success remove role name within team with role name ${getData}",
                onDismiss = {
                    updateDeleteTeamRoleState(
                        TeamDetailState.IdleState
                    )
                }
            )
        }
        is TeamDetailState.ErrorState -> {
            // get error message
            val errNsg = deleteTeamRoleState.errMsg

            // show error dialog
            ErrorDialog(
                errMsg = errNsg,
                onDismiss = {
                    updateDeleteTeamRoleState(
                        TeamDetailState.IdleState
                    )
                }
            )
        }
        is TeamDetailState.LoadingState -> {
            // show loading dialog
            LoadingDialog()
        }
        is TeamDetailState.IdleState -> {

        }
    }

    when(addTeamMemberState) {
        is TeamDetailState.DataState<*> -> {
            // get data
            val getData = addTeamMemberState.data as AssignUserDto

            // show success message
            SuccessDialog(
                successTitle = "Success",
                successMsg = "Success assign user with id ${getData.userId}",
                onDismiss = {
                    updateAddTeamMemberState(
                        TeamDetailState.IdleState
                    )
                }
            )
        }
        is TeamDetailState.ErrorState -> {
            // get error message
            val errNsg = addTeamMemberState.errMsg

            // show error dialog
            ErrorDialog(
                errMsg = errNsg,
                onDismiss = {
                    updateAddTeamMemberState(
                        TeamDetailState.IdleState
                    )
                }
            )
        }
        is TeamDetailState.LoadingState -> {
            // show loading dialog
            LoadingDialog()
        }
        is TeamDetailState.IdleState -> {

        }
    }
}