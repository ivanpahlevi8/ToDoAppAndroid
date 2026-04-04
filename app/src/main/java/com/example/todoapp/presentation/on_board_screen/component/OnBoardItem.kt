package com.example.todoapp.presentation.on_board_screen.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun OnBoardItem(
    item : OnBoardData,
    showNext : Boolean,
    showBack: Boolean,
    showFinish : Boolean,
    onNext : () -> Unit,
    onBack : () -> Unit,
    onFinish : () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.4F),
                            Color.Black.copy(alpha = 0.8F),
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .padding(
                    vertical = Dimension.LARGE_PADDING3,
                    horizontal = Dimension.MEDIUM_PADDING2,
                )
                .align(
                    alignment = Alignment.BottomCenter
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 22.sp,
                    color = colorResource(
                        id = R.color.white,
                    )
                )
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            Text(
                text = item.content,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    color = colorResource(
                        id = R.color.white,
                    )
                )
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING3
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                if(showBack) {
                    Box(
                        modifier = Modifier
                            .padding(
                                Dimension.MEDIUM_PADDING2
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple()
                            ) {
                                onBack()
                            }
                            .shadow(
                                elevation = 4.dp,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.average_end
                                )
                            )
                            .padding(
                                vertical = Dimension.MEDIUM_PADDING1,
                                horizontal = Dimension.LARGE_PADDING2,
                            )
                    ) {
                        Text(
                            text = "Back",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 18.sp,
                                color = colorResource(
                                    id = R.color.white,
                                )
                            )
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.MEDIUM_PADDING2
                            )
                    )
                }

                if(showNext){
                    Box(
                        modifier = Modifier
                            .padding(
                                Dimension.MEDIUM_PADDING2
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple()
                            ) {
                                onNext()
                            }
                            .shadow(
                                elevation = 4.dp,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.excellent_start
                                )
                            )
                            .padding(
                                vertical = Dimension.MEDIUM_PADDING1,
                                horizontal = Dimension.LARGE_PADDING2,
                            )
                    ) {
                        Text(
                            text = "Next",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 18.sp,
                                color = colorResource(
                                    id = R.color.white,
                                )
                            )
                        )
                    }
                }

                if(showFinish) {
                    Box(
                        modifier = Modifier
                            .padding(
                                Dimension.MEDIUM_PADDING2
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple()
                            ) {
                                onFinish()
                            }
                            .shadow(
                                elevation = 4.dp,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.excellent_end
                                )
                            )
                            .padding(
                                vertical = Dimension.MEDIUM_PADDING1,
                                horizontal = Dimension.LARGE_PADDING2,
                            )
                    ) {
                        Text(
                            text = "Finish",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 18.sp,
                                color = colorResource(
                                    id = R.color.white,
                                )
                            )
                        )
                    }
                }
            }
        }
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
                item = onBoardScreenData[0],
                showBack = true,
                showNext = false,
                showFinish = true,
                onBack = {},
                onNext = {},
                onFinish = {}
            )
        }
    }
}