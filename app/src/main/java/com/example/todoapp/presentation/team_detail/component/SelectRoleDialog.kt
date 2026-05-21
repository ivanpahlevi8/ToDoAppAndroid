package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun SelectRoleDialog(
    roleList : List<TeamRoleDto>,
    showDialog : Boolean,
    onDismiss : () -> Unit,
    onSelectRole : (Int) -> Unit,
) {
    AnimatedVisibility(
        visible = showDialog
    ) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = colorResource(
                    id = R.color.time_line_card_color1
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Select Role",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp
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

                    Column(
                        modifier = Modifier
                            .height(
                                200.dp
                            ),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(
                                count = roleList.size
                            ) {
                                index: Int ->
                                val getRole = roleList[index]

                                SelectRoleItem(
                                    role = getRole,
                                    onSelectRole = onSelectRole
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectRoleItem(
    role : TeamRoleDto,
    onSelectRole: (Int) -> Unit
){
    Box(
        modifier = Modifier
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING2,
            )
            .fillMaxWidth()
            .shadow(
                elevation = 3.dp,
                shape = MaterialTheme.shapes.medium
            )
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = colorResource(
                    id = R.color.time_line_card_color4
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = role.roleName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 15.sp
                ),
                color = colorResource(
                    id = R.color.white
                )
            )

            Box(
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .clickable(

                    ) {
                        onSelectRole(role.teamRoleId ?: 0)
                    }
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        color = colorResource(
                            id = R.color.excellent_end
                        )
                    )
                    .padding(
                        Dimension.SMALL_PADDING2
                    )
            ){
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_ic
                    ),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(20.dp),
                    tint = colorResource(
                        id = R.color.black
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SelectRoleDialogPreview(){
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
            SelectRoleDialog(
                roleList = listOf(
                    TeamRoleDto(
                        roleName = "Role 1",
                        teamId = 1
                    ),
                    TeamRoleDto(
                        roleName = "Role 1",
                        teamId = 1
                    ),
                    TeamRoleDto(
                        roleName = "Role 1",
                        teamId = 1
                    ),
                    TeamRoleDto(
                        roleName = "Role 1",
                        teamId = 1
                    ),
                    TeamRoleDto(
                        roleName = "Role 1",
                        teamId = 1
                    ),
                    TeamRoleDto(
                        roleName = "Role 1",
                        teamId = 1
                    )
                ),
                showDialog = true,
                onSelectRole = {},
                onDismiss = {},
            )
        }
    }
}