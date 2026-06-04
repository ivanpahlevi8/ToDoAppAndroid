package com.example.todoapp.presentation.project_to_do.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDoCardContainer(
    text: String,
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .clickable {
                onClick()
            },
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}