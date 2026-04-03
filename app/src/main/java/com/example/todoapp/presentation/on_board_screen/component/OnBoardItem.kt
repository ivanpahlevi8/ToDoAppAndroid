package com.example.todoapp.presentation.on_board_screen.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun OnBoardItem(
    item : OnBoardData
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = item.imgBackground
            ),
            contentDescription = "Image background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun OnBoardItemPreview() {
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
            OnBoardItem(
                item = onBoardScreenData[0]
            )
        }
    }
}