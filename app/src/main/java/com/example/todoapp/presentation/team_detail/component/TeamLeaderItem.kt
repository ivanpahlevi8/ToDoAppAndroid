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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TeamLeaderItem(
    teamLead : UserModel
){
    Box(
        modifier = Modifier
            .shadow(
                elevation = 3.dp,
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
                            id = R.color.blue_light_start
                        ),
                        colorResource(
                            id = R.color.blue_light_end
                        ).copy(
                            alpha = 0.6F
                        )
                    ),
                    start = Offset(0F, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                )
            )
            .padding(
                vertical = Dimension.MEDIUM_PADDING1,
                horizontal = Dimension.SMALL_PADDING2,
            )
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            8.dp
                        )
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(
                                    id = R.color.time_line_card_color5
                                ),
                                colorResource(
                                    id = R.color.time_line_card_color6
                                )
                            ),
                        )
                    )
                    .padding(
                        vertical = 2.dp,
                        horizontal = Dimension.MEDIUM_PADDING1
                    )
            ) {
                Text(
                    text = "Team Leader",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 17.sp,
                    ),
                    color = colorResource(
                        id = R.color.white,
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 3.dp,
                        shape = RoundedCornerShape(
                            5.dp
                        )
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            5.dp
                        )
                    )
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(
                                    id = R.color.time_line_card_color4
                                ),
                                colorResource(
                                    id = R.color.time_line_card_color5
                                )
                            ),
                            start = Offset(0F, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0F)
                        )
                    )
                    .padding(
                        vertical = Dimension.SMALL_PADDING2,
                        horizontal = Dimension.MEDIUM_PADDING2
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
                                70.dp
                            )
                            .shadow(
                                elevation = 2.dp,
                                shape = CircleShape
                            )
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.person_icon
                            ),
                            contentDescription = "Person Icon",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize(),
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            text = "${teamLead.userFirstName} ${teamLead.userLastName}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 20.sp,
                            ),
                            color = colorResource(
                                id = R.color.white
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .height(
                                    Dimension.SMALL_PADDING1
                                )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.email_ic
                                ),
                                contentDescription = "Email Icon",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = colorResource(
                                    id = R.color.white
                                )
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(
                                        Dimension.SMALL_PADDING2
                                    )
                            )

                            Text(
                                text = teamLead.userEmail,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                ),
                                color = colorResource(
                                    id = R.color.white,
                                ),
                                maxLines = 2,
                                lineHeight = TextUnit(15F, TextUnitType.Sp)
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .height(
                                    Dimension.SMALL_PADDING1
                                )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.phone_ic
                                ),
                                contentDescription = "Email Icon",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = colorResource(
                                    id = R.color.white
                                )
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(
                                        Dimension.SMALL_PADDING2
                                    )
                            )

                            Text(
                                text = teamLead.userPhoneNumber,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                ),
                                color = colorResource(
                                    id = R.color.white,
                                ),
                                maxLines = 2,
                                lineHeight = TextUnit(15F, TextUnitType.Sp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = CircleShape
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple()
                            ) {

                            }
                            .padding(
                                Dimension.SMALL_PADDING2
                            )
                            .shadow(
                                elevation = 2.dp,
                                shape = CircleShape
                            )
                            .clip(
                                shape = CircleShape
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.time_line_card_color6
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
                            tint = colorResource(
                                id = R.color.white
                            ),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamLeaderItemPreview(){
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
            TeamLeaderItem(
                teamLead = UserModel(
                    userFirstName = "Ivan",
                    userLastName = "Indirsyah",
                    userCreatedAt = "",
                    userId = "",
                    userName = "ivanpahlevi8",
                    userEmail = "ivan.indirsya@gmail.com",
                    userPhoneNumber = "234325435"
                )
            )
        }
    }
}