package com.example.todoapp.presentation.team_project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.presentation.team_project.component.TeamProjectItemList
import com.example.todoapp.presentation.team_project.component.TeamProjectItemListShimmer

@Composable
fun TeamProjectScreen(
    teamProjectState : TeamProjectState,
    onProjectDetail : (Int) -> Unit,
){
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(
                        Dimension.SMALL_PADDING1
                    ),
                onClick = {

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
                .fillMaxSize()
                .padding(
                    it
                )
        ) {
            when(teamProjectState){
                is TeamProjectState.DataState<*> -> {
                    val getProject = teamProjectState.data
                    val userLeader = teamProjectState.userList

                    TeamProjectItemList(
                        projectList = getProject as List<CreateProjectDto>,
                        leadList = userLeader as List<UserModel>,
                        onProjectDetail = {
                            projectId -> onProjectDetail(projectId)
                        }
                    )
                }
                is TeamProjectState.ErrorState -> {
                    val errMsg = teamProjectState.errMsg

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
                                fontSize = 18.sp,
                            ),
                            color = colorResource(
                                id = R.color.error_color
                            )
                        )
                    }
                }
                is TeamProjectState.LoadingState -> {
                    TeamProjectItemListShimmer()
                }
            }
        }
    }
}