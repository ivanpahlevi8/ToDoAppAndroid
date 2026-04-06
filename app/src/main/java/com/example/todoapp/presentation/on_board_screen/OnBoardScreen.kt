package com.example.todoapp.presentation.on_board_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.presentation.on_board_screen.component.OnBoardData
import com.example.todoapp.presentation.on_board_screen.component.OnBoardItem
import com.example.todoapp.presentation.on_board_screen.component.onBoardScreenData
import com.example.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.launch

@Composable
fun OnBoardScreen(
    onEvent : (OnBoardScreenEvent) -> Unit,
    state : OnBoardScreenState,
){
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) { onBoardScreenData.size }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
        ) {
                index: Int ->
            val getData : OnBoardData = onBoardScreenData[index]

            OnBoardItem(
                item = getData,
                showBack = (index > 0),
                showNext = (index == 0 || index < onBoardScreenData.size - 1),
                showFinish = (index == onBoardScreenData.size - 1),
                onNext = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                },
                onBack = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage - 1
                        )
                    }
                },
                onFinish = {
                    onEvent(
                        OnBoardScreenEvent.BoardEvent
                    )
                }
            )
        }

        when(state) {
            is OnBoardScreenState.LoadingState -> {
                Dialog(
                    onDismissRequest = {}
                ) {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = colorResource(
                            id = R.color.card_background_color2
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(
                                    vertical = Dimension.MEDIUM_PADDING1,
                                    horizontal = Dimension.MEDIUM_PADDING3
                                ),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING2
                                    )
                            )
                            Text(
                                text = "Loading...",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W800,
                                    fontSize = 16.sp,
                                    letterSpacing = 1.1.sp
                                ),
                                color = colorResource(
                                    id = R.color.text_title,
                                )
                            )
                        }
                    }
                }
            }
            is OnBoardScreenState.IdleState -> {

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardScreenPreview() {
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.MEDIUM_PADDING2
                )
        ) {
            OnBoardScreen(
                onEvent = {},
                state = OnBoardScreenState.LoadingState
            )
        }
    }
}