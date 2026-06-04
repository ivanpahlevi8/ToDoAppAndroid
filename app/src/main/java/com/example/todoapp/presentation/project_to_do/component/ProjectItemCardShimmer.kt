package com.example.todoapp.presentation.project_to_do.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.core.component.shimmerEffect
import com.example.todoapp.core.value.Dimension

@Composable
fun ProjectItemCardShimmer(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8)
            )
            .clip(
                shape = RoundedCornerShape(8)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(
                            id = R.color.time_line_card_color1
                        ),
                        colorResource(
                            id = R.color.time_line_card_color2
                        )
                    ),
                    start = Offset(0F, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                )
            )
            .padding(
                vertical = Dimension.MEDIUM_PADDING1,
                horizontal = Dimension.MEDIUM_PADDING2
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier
                    .width(
                        IntrinsicSize.Max
                    )
            ) {
                Box(
                    modifier = Modifier
                        .height(
                            35.dp
                        )
                        .width(
                            120.dp
                        )
                        .clip(
                            shape = RoundedCornerShape(8)
                        )
                        .shimmerEffect()
                )

                HorizontalDivider()
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Box(
                modifier = Modifier
                    .height(
                        50.dp
                    )
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(8)
                    )
                    .shimmerEffect()
            )
        }
    }
}