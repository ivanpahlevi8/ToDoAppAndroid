package com.example.todoapp.presentation.on_board_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.presentation.on_board_screen.component.OnBoardData
import com.example.todoapp.presentation.on_board_screen.component.OnBoardItem
import com.example.todoapp.presentation.on_board_screen.component.onBoardScreenData
import com.example.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.launch

@Composable
fun OnBoardScreen(
    onEvent : (OnBoardScreenEvent) -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) { onBoardScreenData.size }

    HorizontalPager(
        state = pagerState
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
                onEvent = {}
            )
        }
    }
}