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
fun ConnectionUserItem(
    userItem : UserModel,
    isRequest : Boolean,
) {
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
            .background(
                brush = if(isRequest) {
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
                                id = R.color.excellent_start,
                            ),
                            colorResource(
                                id = R.color.excellent_end,
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
            }

            IconButton(
                onClick = {},
                colors = IconButtonColors(
                    containerColor = colorResource(
                        id = if(isRequest) {
                            R.color.excellent_end
                        } else {
                            R.color.error_color
                        }
                    ),
                    contentColor = colorResource(
                        id = R.color.white
                    ),
                    disabledContainerColor = colorResource(
                        id = if(isRequest) {
                            R.color.excellent_end
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
                        id = if(isRequest) {
                            R.drawable.person_add_ic
                        } else {
                            R.drawable.unfriend_ic
                        }
                    ),
                    contentDescription = "Person Add Icon",
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
fun ConnectionUserItemPreview(){
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
            ConnectionUserItem(
                userItem = UserModel(
                    userFirstName = "Ivan",
                    userLastName = "Pahlevi",
                    userCreatedAt = "",
                    userId = "",
                    userName = "ivanpahlevi8",
                    userEmail = "ivan.indirsya@gmail.com",
                    userPhoneNumber = ""
                ),
                isRequest = false
            )
        }
    }
}