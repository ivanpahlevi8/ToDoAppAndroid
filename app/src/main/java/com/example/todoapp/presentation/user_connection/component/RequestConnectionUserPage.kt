package com.example.todoapp.presentation.user_connection.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserConnectionModel
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun RequestConnectionUserPage(
    connectionUserItems : List<UserConnectionModel>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(
            count = connectionUserItems.size
        ) {
            index : Int ->
            val getItem = connectionUserItems[index]

            ConnectionUserItem(
                userItem = getItem.userConnection,
                isRequest = true
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RequestConnectionUserPagePreview() {
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
            RequestConnectionUserPage(
                connectionUserItems = listOf(
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
                )
            )
        }
    }
}