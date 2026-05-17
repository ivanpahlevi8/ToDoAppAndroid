package com.example.todoapp.presentation.team_detail.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.teams.TeamModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TeamDetailHeader(
    teamModel : TeamModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        8.dp
                    )
                )
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(
                        8.dp
                    )
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(
                                id = R.color.time_line_card_color5
                            ),
                            colorResource(
                                id = R.color.time_line_card_color6
                            )
                        ),
                        start = Offset(0F, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0F)
                    )
                )
                .padding(
                    Dimension.MEDIUM_PADDING2
                )
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.groups_ic
                ),
                contentDescription = "Group Icon",
                modifier = Modifier
                    .size(80.dp),
                tint = colorResource(
                    id = R.color.white
                )
            )
        }

        Spacer(
            modifier = Modifier
                .width(
                    Dimension.MEDIUM_PADDING1
                )
        )

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = teamModel.teamName ?: "",
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
                        Dimension.SMALL_PADDING2
                    )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.calendar_month_ic
                    ),
                    contentDescription = "Calendar Month Icon",
                    tint = colorResource(
                        id = R.color.text_title
                    ),
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(
                    modifier = Modifier
                        .width(
                            Dimension.SMALL_PADDING2
                        )
                )

                Text(
                    text = "Team Established At",
                    style = MaterialTheme.typography.titleMedium
                        .copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp,
                        ),
                    color = colorResource(
                        id = R.color.text_title,
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.SMALL_PADDING2
                    )
            )

            Box(
                modifier = Modifier
                    .padding(
                        start = Dimension.SMALL_PADDING2
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            8.dp
                        )
                    )
                    .background(
                        brush = Brush
                            .linearGradient(
                                colors = listOf(
                                    colorResource(
                                        id = R.color.time_line_card_color3
                                    ),
                                    colorResource(
                                        id = R.color.time_line_card_color4
                                    )
                                ),
                                start = Offset(0F, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0F)
                            )
                    )
                    .padding(
                        vertical = Dimension.SMALL_PADDING1,
                        horizontal = Dimension.SMALL_PADDING2
                    )
            ){
                val formattedDate = remember(teamModel.createdAt) {
                    try {
                        teamModel.createdAt?.let {
                            OffsetDateTime.parse(it)
                                .format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy", Locale.getDefault()))
                        } ?: "Date unknown" // Fallback text
                    } catch (e: Exception) {
                        "Invalid date" // Prevent crash on bad string format
                    }
                }
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold, // Using FontWeight.Bold is more readable than W700
                        fontSize = 18.sp,
                    ),
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}