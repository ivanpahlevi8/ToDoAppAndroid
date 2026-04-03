package com.example.todoapp.core.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import com.example.todoapp.R

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed{
    val transition = rememberInfiniteTransition()

    val alphaValue = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value

    background(
        color = colorResource(
            id = R.color.shimmer
        ).copy(alpha = alphaValue)
    )
}