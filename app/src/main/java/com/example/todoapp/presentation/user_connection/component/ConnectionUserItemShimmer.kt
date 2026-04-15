package com.example.todoapp.presentation.user_connection.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.component.shimmerEffect
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun ConnectionUserItemShimmer(
    isRequest : Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
            )
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                brush = if(isRequest) {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(
                                id = R.color.average_start,
                            ),
                            colorResource(
                                id = R.color.average_end,
                            ),
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY), // Bottom-left
                        end = Offset(Float.POSITIVE_INFINITY, 0f)    // Top-right
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(
                                id = R.color.excellent_start,
                            ),
                            colorResource(
                                id = R.color.excellent_end,
                            ),
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY), // Bottom-left
                        end = Offset(Float.POSITIVE_INFINITY, 0f)    // Top-right
                    )
                },
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                vertical = Dimension.MEDIUM_PADDING1,
                horizontal = Dimension.MEDIUM_PADDING2
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.person_icon
                ),
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(
                        70.dp
                    )
                    .clip(
                        shape = CircleShape
                    )
            )

            Column(
                modifier = Modifier
                    .weight(
                        1f
                    )
                    .padding(
                        horizontal = Dimension.MEDIUM_PADDING2
                    )
            ) {
                Box(
                    modifier = Modifier
                        .height(35.dp)
                        .width(100.dp)
                        .shimmerEffect()
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(110.dp)
                        .shimmerEffect()
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Box(
                    modifier = Modifier
                        .height(35.dp)
                        .width(140.dp)
                        .shimmerEffect()
                )
            }

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(
                        shape = CircleShape
                    )
                    .shimmerEffect()
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ConnectionUserItemShimmerPreview() {
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .padding(
                    Dimension.MEDIUM_PADDING1
                )
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            ConnectionUserItemShimmer(
                isRequest = true
            )
        }
    }
}