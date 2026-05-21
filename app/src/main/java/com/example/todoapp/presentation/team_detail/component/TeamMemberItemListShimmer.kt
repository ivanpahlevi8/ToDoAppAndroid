package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.todoapp.core.component.shimmerEffect
import com.example.todoapp.core.value.Dimension

@Composable
fun TeamMemberItemListShimmer(){
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
                            id = R.color.time_line_card_color1
                        ),
                        colorResource(
                            id = R.color.time_line_card_color2
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
                .fillMaxSize()
                .padding(
                    horizontal = Dimension.SMALL_PADDING2
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                                        id = R.color.time_line_card_color3
                                    ),
                                    colorResource(
                                        id = R.color.time_line_card_color4
                                    )
                                ),
                                start = Offset(0F, 0F),
                                end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                            )
                        )
                        .padding(
                            vertical = Dimension.SMALL_PADDING1,
                            horizontal = Dimension.MEDIUM_PADDING2
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(
                                170.dp
                            )
                            .clip(
                                shape = RoundedCornerShape(
                                    7.dp
                                )
                            )
                            .shimmerEffect()
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(
                            Dimension.SMALL_PADDING2
                        )
                        .clickable{

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
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.add_ic
                        ),
                        contentDescription = "Add Icon",
                        modifier = Modifier
                            .size(18.dp),
                        tint = colorResource(
                            id = R.color.black
                        )
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start
            ) {
                items(
                    count = 5
                ) {
                    TeamMemberItemShimmer()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamMemberItemListShimmerPreview(){
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background
            )
            .padding(
                Dimension.SMALL_PADDING2
            )
    ) {
        TeamMemberItemListShimmer()
    }
}