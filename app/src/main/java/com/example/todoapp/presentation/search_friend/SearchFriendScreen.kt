package com.example.todoapp.presentation.search_friend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todoapp.presentation.search_friend.component.SearchFriendPage

@Composable
fun SearchFriendScreen(
    onBack : () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchFriendPage(
            userItem = listOf(),
            onBack = {
                onBack()
            }
        )
    }
}