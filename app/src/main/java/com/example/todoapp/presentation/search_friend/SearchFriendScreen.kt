package com.example.todoapp.presentation.search_friend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.presentation.search_friend.component.SearchFriendPage
import com.example.todoapp.presentation.search_friend.component.SearchFriendPageShimmer

@Composable
fun SearchFriendScreen(
    onBack : () -> Unit,
    onEvent : (SearchFriendEvent) -> Unit,
    searchState : SearchFriendState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when(searchState){
            is SearchFriendState.DataState -> {
                val getData = searchState.data

                SearchFriendPage(
                    userItem = listOf(
                        getData
                    ),
                    onBack = {
                        onBack()
                    },
                    onSearch = {
                            text -> onEvent(
                        SearchFriendEvent.OnSearch(
                            userName = text
                        )
                    )
                    }
                )
            }
            is SearchFriendState.ErrorState -> {
                val errMsg = searchState.errMsg

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errMsg,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            fontSize = 18.sp
                        ),
                        color = colorResource(
                            id = R.color.error_color
                        )
                    )
                }
            }
            is SearchFriendState.LoadingState -> {
                SearchFriendPageShimmer()
            }
            is SearchFriendState.IdleState -> {
                SearchFriendPage(
                    userItem = listOf(

                    ),
                    onBack = {
                        onBack()
                    },
                    onSearch = {
                            text -> onEvent(
                        SearchFriendEvent.OnSearch(
                            userName = text
                        )
                    )
                    }
                )
            }
        }
    }
}