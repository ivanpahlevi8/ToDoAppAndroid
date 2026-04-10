package com.example.todoapp.presentation.search_friend.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun SearchFriendPage(
    userItem : List<UserModel>,
    onBack : () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    180.dp
                )
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.people_background
                ),
                contentDescription = "People background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom =
                            Dimension.LARGE_PADDING3
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom =
                        Dimension.LARGE_PADDING3
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Gray.copy(
                                    alpha = 0.4F
                                )
                            )
                        )
                    )
            )

            TextField(
                value = "",
                onValueChange = {},
                shape = MaterialTheme.shapes.medium,
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.search_ic
                        ),
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(
                        vertical = Dimension.MEDIUM_PADDING1,
                        horizontal = Dimension.LARGE_PADDING1,
                    )
            )

            IconButton(
                onClick = {
                    onBack()
                },
                modifier = Modifier
                    .align(
                        alignment = Alignment.TopStart
                    )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_back_ic
                    ),
                    contentDescription = "",
                    tint = colorResource(
                        id = R.color.black
                    ),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(
                    1f
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            items(
                count = userItem.size
            ) {
                index : Int ->
                SearchFriendItem(
                    userItem = userItem[index]
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchFriendPagePreview() {
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            SearchFriendPage(
                userItem = listOf(
                    UserModel(
                        userFirstName = "ivan",
                        userName = "ivanpahlevi8",
                        userLastName = "indirsyah",
                        userCreatedAt = "2026-03-31T06:51:02.3742565",
                        userId = "d6f7b560-3469-4742-9ae0-be4bfdadbdca",
                        userEmail = "wqerew@aree.com",
                        userPhoneNumber = "2534563"
                    )
                ),
                onBack = {}
            )
        }
    }
}