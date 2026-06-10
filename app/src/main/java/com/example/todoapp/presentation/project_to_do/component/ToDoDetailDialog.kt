package com.example.todoapp.presentation.project_to_do.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.presentation.project_to_do.ToDoStatusEnum

@Composable
fun ToDoDetailDialog(
    toDoDto: CreateToDoDto,
    showDialog : Boolean,
    onDismiss : () -> Unit
){
    AnimatedVisibility(
        showDialog
    ) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = colorResource(
                    id = R.color.card_information_background1
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = Dimension.MEDIUM_PADDING1,
                            horizontal = Dimension.MEDIUM_PADDING2
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = toDoDto.toDoItemName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 21.sp
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    Text(
                        text = toDoDto.toDoItemDescription,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        ),
                        textAlign = TextAlign.Justify
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.calendar_month_ic
                            ),
                            contentDescription = "Calendar Month Icon",
                            modifier = Modifier
                                .size(18.dp),
                            tint = colorResource(
                                id = R.color.text_title
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .width(
                                    Dimension.SMALL_PADDING2
                                )
                        )

                        Text(
                            text = toDoDto.toDoCreatedAt ?: "No Date",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W800,
                                fontSize = 16.sp
                            ),
                            color = colorResource(
                                id = R.color.text_title,
                            )
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.info_ic
                            ),
                            contentDescription = "Info Icon",
                            modifier = Modifier
                                .size(18.dp),
                            tint = colorResource(
                                id = R.color.text_title
                            )
                        )

                        Spacer(
                            modifier = Modifier
                                .width(
                                    Dimension.SMALL_PADDING2
                                )
                        )

                        Box(
                            modifier = Modifier
                                .clip(
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(
                                    color = colorResource(
                                        id = when(toDoDto.toDoItemState) {
                                            ToDoStatusEnum.CREATED.label -> {
                                                R.color.bad_start
                                            }
                                            ToDoStatusEnum.PROCESSED.label -> {
                                                R.color.average_end
                                            }
                                            ToDoStatusEnum.FINISHED.label -> {
                                                R.color.excellent_end
                                            }
                                            else -> {R.color.bad_start}
                                        }
                                    )
                                )
                                .padding(
                                    vertical = 2.dp,
                                    horizontal = Dimension.SMALL_PADDING1
                                )
                        ) {
                            Text(
                                text = toDoDto.toDoItemState ?: "",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp
                                ),
                                color = colorResource(
                                    id = R.color.white
                                )
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                onDismiss()
                            }
                            .padding(
                                Dimension.SMALL_PADDING1
                            )
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.time_line_card_color1
                                )
                            )
                            .padding(
                                vertical = Dimension.SMALL_PADDING1,
                                horizontal = Dimension.MEDIUM_PADDING2
                            )
                    ) {
                        Text(
                            text = "OK",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 16.sp
                            ),
                            color = colorResource(
                                id = R.color.white
                            )
                        )
                    }
                }
            }
        }
    }
}