package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

@Composable
fun TeamRoleItemList(
    roleList : List<TeamRoleDto>,
    showTeamRoleDialog : () -> Unit,
){
    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(
                    4.dp
                )
            )
            .clip(
                shape = RoundedCornerShape(
                    4.dp
                )
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(
                            id = R.color.time_line_card_color2
                        ),
                        colorResource(
                            id = R.color.time_line_card_color3
                        )
                    ),
                    start = Offset(0F, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0F),
                )
            )
            .padding(
                vertical = Dimension.MEDIUM_PADDING1
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Dimension.SMALL_PADDING2
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(
                                6.dp
                            )
                        )
                        .clip(
                            shape = RoundedCornerShape(
                                6.dp
                            )
                        )
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    colorResource(
                                        id = R.color.blue_light_start
                                    ),
                                    colorResource(
                                        id = R.color.blue_light_end
                                    )
                                ),
                                start = Offset(0F, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0F)
                            )
                        )
                        .padding(
                            vertical = Dimension.SMALL_PADDING1,
                            horizontal = Dimension.MEDIUM_PADDING2
                        )
                ){
                    Text(
                        text = "Role On The Team",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                        ),
                        color = colorResource(
                            id = R.color.white
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(
                            shape = CircleShape
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            showTeamRoleDialog()
                        }
                        .padding(
                            Dimension.SMALL_PADDING2
                        )
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
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.add_ic
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(18.dp),
                        tint = colorResource(
                            id = R.color.black
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.SMALL_PADDING2
                    )
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(
                    count = roleList.size
                ) {
                        index : Int ->
                    val getData = roleList[index]

                    TeamRoleItem(
                        teamRoleDto = getData
                    ) { }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamRoleItemListPreview(){
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background
            )
            .padding(
                Dimension.SMALL_PADDING2
            )
    ) {
        TeamRoleItemList(
            roleList = listOf(
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
            ),
            showTeamRoleDialog = {}
        )
    }
}