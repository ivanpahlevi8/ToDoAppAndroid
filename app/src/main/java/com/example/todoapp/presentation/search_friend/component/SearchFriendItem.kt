package com.example.todoapp.presentation.search_friend.component

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
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
fun SearchFriendItem(
    userItem : UserModel
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
                horizontal = Dimension.MEDIUM_PADDING1
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.person_icon
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        shape = MaterialTheme.shapes.medium
                    )
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal =
                            Dimension.MEDIUM_PADDING2
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${userItem.userFirstName} ${userItem.userLastName}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W900,
                        fontSize = 20.sp,
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Text(
                    text = userItem.userName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W600,
                        fontSize = 18.sp
                    ),
                    color = colorResource(
                        id = R.color.text_title,
                    )
                )
            }

            IconButton(
                onClick = {},
                Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                    color = colorResource(
                        id = R.color.excellent_end
                    )
                )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.person_add_ic
                    ),
                    contentDescription = "Icon Add Person"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchFriendItemPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            SearchFriendItem(
                userItem = UserModel(
                    userFirstName = "ivan",
                    userName = "ivanpahlevi8",
                    userLastName = "indirsyah",
                    userCreatedAt = "2026-03-31T06:51:02.3742565",
                    userId = "d6f7b560-3469-4742-9ae0-be4bfdadbdca",
                    userEmail = "wqerew@aree.com",
                    userPhoneNumber = "2534563"
                )
            )
        }
    }
}