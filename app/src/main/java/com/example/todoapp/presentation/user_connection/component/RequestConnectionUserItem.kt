package com.example.todoapp.presentation.user_connection.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
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
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun RequestConnectionUserItem(
    userItem : UserModel,
    onUnFollow : (String) -> Unit,
    onAcceptFriend : (String) -> Unit,
    onDeclined : (String) -> Unit,
    connectionId : String,
    isRequestFromUser : Boolean,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
            )
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium,
            )
            .background(
                brush = if(isRequestFromUser) {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(
                                id = R.color.average_start,
                            ),
                            colorResource(
                                id = R.color.average_end,
                            ),
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY), // Bottom-left
                        end = Offset(Float.POSITIVE_INFINITY, 0f)    // Top-right
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(
                                id = R.color.bad_start,
                            ),
                            colorResource(
                                id = R.color.bad_end,
                            ),
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY), // Bottom-left
                        end = Offset(Float.POSITIVE_INFINITY, 0f)    // Top-right
                    )
                },
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
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.person_icon
                ),
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(
                        70.dp
                    )
                    .clip(
                        shape = CircleShape
                    )
            )

            Column(
                modifier = Modifier
                    .weight(
                        1f
                    )
                    .padding(
                        horizontal = Dimension.MEDIUM_PADDING2
                    )
            ) {
                Text(
                    text = "${userItem.userFirstName} ${userItem.userLastName}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W900,
                        fontSize = 18.sp
                    ),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Text(
                    text = userItem.userName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp
                    ),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Text(
                    text = userItem.userEmail,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp
                    ),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING2
                        )
                )

                if(isRequestFromUser){
                    Box(
                        modifier = Modifier
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .shadow(
                                elevation = 2.dp
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.average_start
                                )
                            )
                            .padding(
                                horizontal = Dimension.MEDIUM_PADDING1
                            )
                    ) {
                        Text(
                            text = "Follow ${userItem.userName}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 13.sp,
                            ),
                            color = colorResource(
                                id = R.color.text_title,
                            )
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .shadow(
                                elevation = 2.dp
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.card_information_background1
                                )
                            )
                            .padding(
                                horizontal = Dimension.MEDIUM_PADDING1
                            )
                    ) {
                        Text(
                            text = "Follow you",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 13.sp,
                            ),
                            color = colorResource(
                                id = R.color.white,
                            )
                        )
                    }
                }
            }

            Column {
                IconButton(
                    onClick = {
                        if(isRequestFromUser) {
                            onUnFollow(
                                connectionId
                            )
                        } else {
                            onDeclined(
                                connectionId
                            )
                        }
                    },
                    colors = IconButtonColors(
                        containerColor = colorResource(
                            id = if(isRequestFromUser) {
                                R.color.error_color
                            } else {
                                R.color.error_color
                            }
                        ),
                        contentColor = colorResource(
                            id = R.color.white
                        ),
                        disabledContainerColor = colorResource(
                            id = if(isRequestFromUser) {
                                R.color.error_color
                            } else {
                                R.color.error_color
                            }
                        ),
                        disabledContentColor = colorResource(
                            id = R.color.white
                        ),
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            id = if(isRequestFromUser) {
                                R.drawable.unfriend_ic
                            } else {
                                R.drawable.cancel_ic
                            }
                        ),
                        contentDescription = "Person Add Icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.white
                        )
                    )
                }

                if(!isRequestFromUser) {
                    IconButton(
                        onClick = {
                            onAcceptFriend(
                                connectionId
                            )
                        },
                        colors = IconButtonColors(
                            containerColor = colorResource(
                                id = R.color.excellent_end
                            ),
                            contentColor = colorResource(
                                id = R.color.white
                            ),
                            disabledContainerColor = colorResource(
                                id = R.color.excellent_end
                            ),
                            disabledContentColor = colorResource(
                                id = R.color.white
                            ),
                        )
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.check_circle_ic
                            ),
                            contentDescription = "Person Add Icon",
                            modifier = Modifier
                                .size(20.dp),
                            tint = colorResource(
                                id = R.color.white
                            )
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
fun RequestConnectionUserItemPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .padding(
                    Dimension.SMALL_PADDING2
                )
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ){
            RequestConnectionUserItem(
                userItem = UserModel(
                    userFirstName = "Ivan",
                    userLastName = "Pahlevi",
                    userCreatedAt = "",
                    userId = "",
                    userName = "ivanpahlevi8",
                    userEmail = "ivan.indirsya@gmail.com",
                    userPhoneNumber = ""
                ),
                onUnFollow = {},
                onAcceptFriend = {},
                isRequestFromUser = false,
                connectionId = "",
                onDeclined = {}
            )
        }
    }
}