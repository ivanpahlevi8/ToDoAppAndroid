package com.example.todoapp.presentation.user_connection.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserConnectionModel
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun ConnectionUserPage(
    userItems : List<UserConnectionModel>,
    onUnfriend : (String) -> Unit,
    onAcceptFriend : (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(
            count = userItems.size
        ) {
            index : Int ->
            val getData = userItems[index]

            ConnectionUserItem(
                userItem = getData.userConnection,
                isRequest = false,
                onUnfriend = {
                    onUnfriend(it)
                },
                onAcceptFriend = {
                    onAcceptFriend(it)
                },
                connectionId = getData.connectionId
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ConnectionUserPagePreview() {
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
            ConnectionUserPage(
                userItems = listOf(
                    UserConnectionModel(
                        connectionId = "",
                        userConnection = UserModel(
                            userFirstName = "Ivan",
                            userLastName = "Pahlevi",
                            userCreatedAt = "",
                            userId = "",
                            userName = "ivanpahlevi8",
                            userEmail = "ivan.indirsya@gmail.com",
                            userPhoneNumber = ""
                        ),
                    ),
                    UserConnectionModel(
                        connectionId = "",
                        userConnection = UserModel(
                            userFirstName = "Ivan",
                            userLastName = "Pahlevi",
                            userCreatedAt = "",
                            userId = "",
                            userName = "ivanpahlevi8",
                            userEmail = "ivan.indirsya@gmail.com",
                            userPhoneNumber = ""
                        ),
                    ),
                    UserConnectionModel(
                        connectionId = "",
                        userConnection = UserModel(
                            userFirstName = "Ivan",
                            userLastName = "Pahlevi",
                            userCreatedAt = "",
                            userId = "",
                            userName = "ivanpahlevi8",
                            userEmail = "ivan.indirsya@gmail.com",
                            userPhoneNumber = ""
                        ),
                    )
                ),
                onUnfriend = {},
                onAcceptFriend = {}
            )
        }
    }
}