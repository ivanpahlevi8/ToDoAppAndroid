package com.example.todoapp.presentation.team_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.component.ErrorDialog
import com.example.todoapp.core.component.LoadingDialog
import com.example.todoapp.core.component.SuccessDialog
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.presentation.team_detail.component.TeamDetailPage
import com.example.todoapp.presentation.team_detail.component.TeamDetailPageShimmer

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeamDetailScreen(
    state : TeamDetailState,
    teamRoleListState : TeamDetailState,
    createTeamRoleState : TeamDetailState,
    updateCreateTeamRoleState : (TeamDetailState) -> Unit,
    onEvent: (TeamDetailEvent) -> Unit,
) {
    TeamDetailPage(
        teamModelState = state,
        teamRoleState = teamRoleListState,
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
}