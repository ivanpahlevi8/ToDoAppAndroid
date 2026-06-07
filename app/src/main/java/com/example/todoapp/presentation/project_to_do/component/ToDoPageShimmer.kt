package com.example.todoapp.presentation.project_to_do.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.todoapp.core.component.shimmerEffect

@Composable
fun ToDoPageShimmer(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .shimmerEffect()
    )
}