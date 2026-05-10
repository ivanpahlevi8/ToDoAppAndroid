package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
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
import com.example.todoapp.core.component.shimmerEffect
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TeamDetailPageShimmer(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
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
                Box(
                    modifier = Modifier
                        .height(
                            40.dp
                        )
                        .width(140.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                8.dp
                            )
                        )
                        .shimmerEffect()
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
                            vertical = Dimension.SMALL_PADDING2,
                            horizontal = Dimension.SMALL_PADDING2
                        )
                ){
                    Box(
                        modifier = Modifier
                            .height(25.dp)
                            .width(100.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    6.dp
                                )
                            )
                            .shimmerEffect()
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .height(
                    Dimension.MEDIUM_PADDING3
                )
        )

        TeamRoleItemListShimmer()

        Spacer(
            modifier = Modifier
                .height(
                    Dimension.MEDIUM_PADDING3
                )
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            TeamMemberItemListShimmer()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamDetailPageShimmerPreview(){
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
            TeamDetailPageShimmer()
        }
    }
}