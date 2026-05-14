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
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.presentation.team_detail.component.TeamDetailPage
import com.example.todoapp.presentation.team_detail.component.TeamDetailPageShimmer

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeamDetailScreen(
    state : TeamDetailState
) {
    when(state) {
        is TeamDetailState.DataState<*> -> {
            val getData = state.data as TeamModel
            val getRole = state.roleModel

            TeamDetailPage(
                teamModel = getData,
                roleList = getRole ?: listOf()
            )
        }
        is TeamDetailState.ErrorState -> {
            // get error message
            val errMsg = state.errMsg

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = Dimension.SMALL_PADDING2
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errMsg,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 17.sp
                    ),
                    color = colorResource(
                        id = R.color.error_color
                    )
                )
            }
        }
        is TeamDetailState.LoadingState -> {
            TeamDetailPageShimmer()
        }
        is TeamDetailState.IdleState -> {}
    }
}