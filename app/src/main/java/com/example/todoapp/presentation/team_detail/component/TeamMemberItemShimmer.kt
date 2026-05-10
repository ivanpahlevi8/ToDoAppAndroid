package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun TeamMemberItemShimmer(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
            )
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium
            )
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = colorResource(
                    id = R.color.card_background_color2
                )
            )
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING2,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(
                        50.dp
                    )
                    .shadow(
                        elevation = 2.dp,
                        shape = CircleShape
                    )
                    .clip(
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.person_icon
                    ),
                    contentDescription = "Person Icon",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
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
                    .weight(
                        1f
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .height(
                            30.dp
                        )
                        .width(170.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                6.dp
                            )
                        )
                        .shimmerEffect()
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            2.dp
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
                                        id = R.color.time_line_card_color1
                                    ),
                                    colorResource(
                                        id = R.color.time_line_card_color2
                                    )
                                ),
                                start = Offset(0F, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0F)
                            )
                        )
                        .padding(
                            vertical = 2.dp,
                            horizontal = Dimension.MEDIUM_PADDING1
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .height(
                                23.dp
                            )
                            .width(80.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    8.dp
                                )
                            )
                            .shimmerEffect()
                    )

                }

                Spacer(
                    modifier = Modifier
                        .height(
                            2.dp
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.email_ic
                        ),
                        contentDescription = "Email Icon",
                        modifier = Modifier
                            .size(12.dp),
                        tint = colorResource(
                            id = R.color.text_title,
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                2.dp
                            )
                    )

                    Box(
                        modifier = Modifier
                            .height(
                                25.dp
                            )
                            .width(120.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    6.dp
                                )
                            )
                            .shimmerEffect()
                    )

                }
            }

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                        }
                        .padding(
                            Dimension.SMALL_PADDING2
                        )
                        .clip(
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(
                                10.dp
                            )
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
                            id = R.drawable.info_ic
                        ),
                        contentDescription = "Info Icon",
                        modifier = Modifier
                            .size(18.dp),
                        tint = colorResource(
                            id = R.color.white
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                        }
                        .padding(
                            Dimension.SMALL_PADDING2
                        )
                        .clip(
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .background(
                            color = colorResource(
                                id = R.color.error_color
                            )
                        )
                        .padding(
                            Dimension.SMALL_PADDING2
                        )
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.delete_forever_ic
                        ),
                        contentDescription = "Info Icon",
                        modifier = Modifier
                            .size(18.dp),
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
fun TeamMemberItemShimmerPreview(){
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
            TeamMemberItemShimmer()
        }
    }
}