package com.example.todoapp.presentation.search_friend

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat.getDrawable
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.presentation.search_friend.component.SearchFriendPage
import com.example.todoapp.presentation.search_friend.component.SearchFriendPageShimmer
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun SearchFriendScreen(
    onBack : () -> Unit,
    onEvent : (SearchFriendEvent) -> Unit,
    searchState : SearchFriendState,
    addFriendState : SearchFriendState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when(searchState){
            is SearchFriendState.DataState<*> -> {
                val getData = searchState.data

                SearchFriendPage(
                    userItem = listOf(
                        getData as UserModel
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
                    },
                    onAddFriend = {
                            friendId -> onEvent(
                                SearchFriendEvent.OnAddFriend(
                                    friendId
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
                    },
                    onAddFriend = {
                        friendId -> SearchFriendEvent.OnAddFriend(
                            friendId
                        )
                    }
                )
            }
        }

        when(addFriendState) {
            is SearchFriendState.DataState<*> -> {
                Dialog(
                    onDismissRequest = {}
                ) {
                    Surface(
                        modifier = Modifier
                            .width(180.dp),
                        shape = MaterialTheme.shapes.medium,
                        color = colorResource(
                            id = R.color.card_information_background1
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimension.MEDIUM_PADDING1
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberDrawablePainter(
                                    drawable = getDrawable(
                                        LocalContext.current,
                                        R.drawable.check_ok
                                    ),
                                ),
                                contentDescription = "Check Gift",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(
                                        shape = CircleShape
                                    )
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING1
                                    )
                            )

                            Text(
                                text = "Successs add friend",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 20.sp,
                                ),
                                color = colorResource(
                                    id = R.color.text_title
                                ),
                                textAlign = TextAlign.Justify,
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING2
                                    )
                            )

                            Button(
                                onClick = {},
                                colors = ButtonColors(
                                    containerColor = colorResource(
                                        id = R.color.excellent_end
                                    ),
                                    disabledContentColor = colorResource(
                                        id = R.color.excellent_end
                                    ),
                                    contentColor = colorResource(
                                        id = R.color.white
                                    ),
                                    disabledContainerColor = colorResource(
                                        id = R.color.white
                                    )
                                )
                            ) {
                                Text(
                                    text = "Ok",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                    ),
                                    color = colorResource(
                                        id = R.color.white
                                    ),
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                }
            }
            is SearchFriendState.ErrorState -> {
                Dialog(
                    onDismissRequest = {}
                ) {
                    Surface(
                        modifier = Modifier
                            .width(180.dp),
                        shape = MaterialTheme.shapes.medium,
                        color = colorResource(
                            id = R.color.card_information_background1
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimension.MEDIUM_PADDING1
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberDrawablePainter(
                                    drawable = getDrawable(
                                        LocalContext.current,
                                        R.drawable.x_error
                                    ),
                                ),
                                contentDescription = "Check Gift",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(
                                        shape = CircleShape
                                    )
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING1
                                    )
                            )

                            Text(
                                text = addFriendState.errMsg,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 20.sp,
                                ),
                                color = colorResource(
                                    id = R.color.text_title
                                ),
                                textAlign = TextAlign.Justify,
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING2
                                    )
                            )

                            Button(
                                onClick = {},
                                colors = ButtonColors(
                                    containerColor = colorResource(
                                        id = R.color.error_color
                                    ),
                                    disabledContentColor = colorResource(
                                        id = R.color.error_color
                                    ),
                                    contentColor = colorResource(
                                        id = R.color.white
                                    ),
                                    disabledContainerColor = colorResource(
                                        id = R.color.white
                                    )
                                )
                            ) {
                                Text(
                                    text = "Ok",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                    ),
                                    color = colorResource(
                                        id = R.color.text_title
                                    ),
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                }
            }
            is SearchFriendState.LoadingState -> {
                Dialog(
                    onDismissRequest = {}
                ) {
                    Surface(
                        modifier = Modifier
                            .size(120.dp),
                        shape = MaterialTheme.shapes.medium,
                        color = colorResource(
                            id = R.color.card_information_background1
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    horizontal = Dimension.MEDIUM_PADDING1
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            is SearchFriendState.IdleState -> {

            }
        }
    }
}