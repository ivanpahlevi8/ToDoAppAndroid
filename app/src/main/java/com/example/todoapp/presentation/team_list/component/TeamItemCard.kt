package com.example.todoapp.presentation.team_list.component

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TeamItemCard(
    teamModel: TeamModel,
    isTeamLead : Boolean,
    onTeamDetail : (Int) -> Unit,
){
    Box(
        modifier = Modifier
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING2
            )
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(
                            id = R.color.blue_light_start,
                        ),
                        colorResource(
                            id = R.color.blue_light_end,
                        ),
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY), // Bottom-left
                    end = Offset(Float.POSITIVE_INFINITY, 0f)    // Top-right
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                vertical = Dimension.MEDIUM_PADDING1,
                horizontal = Dimension.MEDIUM_PADDING2
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .weight(
                        1f
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = teamModel.teamName ?: "No Team Name",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 20.sp
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING2
                        )
                )

                Text(
                    text = teamModel.teamDescription ?: "No Description",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 14.sp,
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    ),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING2
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Lead by : ",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W800,
                            fontSize = 14.sp
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .shadow(
                                elevation = 2.dp,
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(
                                            id = R.color.excellent_start
                                        ),
                                        colorResource(
                                            id = R.color.excellent_end
                                        )
                                    ),
                                    start = Offset(0F, Float.POSITIVE_INFINITY),
                                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                                )
                            )
                            .padding(
                                horizontal = Dimension.MEDIUM_PADDING1
                            )
                    ) {
                        Text(
                            text = teamModel.teamLeader?.userName ?: "",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 12.sp
                            ),
                            color = colorResource(
                                id = R.color.black
                            )
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.MEDIUM_PADDING2
                    )
            )
            
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // info button
                Box(
                    modifier = Modifier
                        .clickable{
                            onTeamDetail(teamModel.teamId ?: 0)
                        }
                        .padding(
                            Dimension.SMALL_PADDING1
                        )
                        .shadow(
                            elevation = 2.dp,
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
                            color = colorResource(
                                id = R.color.average_end
                            )
                        )
                        .padding(
                            Dimension.SMALL_PADDING1
                        )
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.info_ic
                        ),
                        contentDescription = "Info Icon",
                        modifier = Modifier
                            .size(28.dp),
                        tint = colorResource(
                            id = R.color.white
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING2
                        )
                )

                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
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
                            color = colorResource(
                                id = R.color.error_dialog_background
                            )
                        )
                        .padding(
                            Dimension.SMALL_PADDING1
                        )
                        .clickable{

                        }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if(isTeamLead) {
                                R.drawable.delete_forever_ic
                            } else {
                                R.drawable.exit_to_app_ic
                            }
                        ),
                        contentDescription = "Info Icon",
                        modifier = Modifier
                            .size(28.dp),
                        tint = colorResource(
                            id = R.color.white
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamItemListPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.MEDIUM_PADDING2
                )
        ) {
            TeamItemCard (
                teamModel = TeamModel(
                    teamName = "Tim Pembuatan Mobile App",
                    teamDescription = "Tim kerja yang berfokus pada pembuatan aplikasi mobile app",
                    teamLeader = UserModel(
                        userFirstName = "",
                        userLastName = "",
                        userCreatedAt = "",
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "",
                        userPhoneNumber = ""
                    ),
                ),
                isTeamLead = true,
                onTeamDetail = {}
            )
        }
    }
}