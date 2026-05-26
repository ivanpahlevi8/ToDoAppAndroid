package com.example.todoapp.presentation.team_project.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.component.shimmerEffect
import com.example.todoapp.core.value.Constants
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TeamProjectItemShimmer(){
    Box(
        modifier = Modifier
            .padding(
                Dimension.MEDIUM_PADDING1
            )
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium
            )
            .clip(
                shape = MaterialTheme.shapes.medium
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
                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                )
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
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier
                        .width(
                            IntrinsicSize.Max
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .height(
                                35.dp
                            )
                            .width(
                                120.dp
                            )
                            .clip(
                                shape = RoundedCornerShape(6.dp)
                            )
                            .shimmerEffect()
                    )

                    HorizontalDivider(
                        thickness = 1.5.dp,
                        color = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING2
                        )
                )

                Row(
                    modifier = Modifier
                        .padding(
                            start = 2.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Project Description",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
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
                                shape = CircleShape
                            )
                            .clickable {

                            }
                            .padding(
                                Dimension.SMALL_PADDING1
                            )
                            .clip(
                                shape = CircleShape
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.time_line_card_color4
                                )
                            )
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.keyboard_arrow_down_ic
                            ),
                            contentDescription = "Arrow Indicator",
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
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.person_ic
                        ),
                        contentDescription = "Person Icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(Dimension.SMALL_PADDING2)
                    )

                    Text(
                        text = "Lead By",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
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
                            .height(
                                30.dp
                            )
                            .width(
                                100.dp
                            )
                            .clip(
                                shape = RoundedCornerShape(6.dp)
                            )
                            .shimmerEffect()
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.calendar_month_ic
                        ),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Text(
                        text = "Created At",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
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
                            .height(
                                30.dp
                            )
                            .width(
                                100.dp
                            )
                            .clip(
                                shape = RoundedCornerShape(6.dp)
                            )
                            .shimmerEffect()
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.check_circle_ic
                        ),
                        contentDescription = "Check circle icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Text(
                        text = "Project Status",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
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
                            .height(
                                30.dp
                            )
                            .width(
                                100.dp
                            )
                            .clip(
                                shape = RoundedCornerShape(6.dp)
                            )
                            .shimmerEffect()
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                    }
                    .padding(
                        Dimension.SMALL_PADDING1
                    )
                    .clip(
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        color = colorResource(
                            id = R.color.average_end
                        )
                    )
                    .padding(
                        Dimension.SMALL_PADDING2
                    )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.info_ic
                    ),
                    contentDescription = "Info Icon",
                    modifier = Modifier
                        .size(22.dp),
                    tint = colorResource(
                        id = R.color.white
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamProjectItemShimmerPreview(){
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
            TeamProjectItemShimmer()
        }
    }
}