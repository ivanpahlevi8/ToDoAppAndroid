package com.example.todoapp.presentation.to_do.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.component.DialogInputTextField
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateToDoDto
import kotlin.random.Random

@Composable
fun AddToDoDialog(
    showDialog : Boolean,
    onDismiss : () -> Unit,
    onCreateDialog : (CreateToDoDto) -> Unit,
){
    // create input for title and description
    var inputToDoTitle by remember { mutableStateOf("") }
    var inputToDoDescription by remember { mutableStateOf("") }

    AnimatedVisibility(
        visible = showDialog
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
                            vertical = Dimension.SMALL_PADDING2,
                            horizontal = Dimension.MEDIUM_PADDING1
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Create To Do",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 20.sp
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING2
                            )
                    )

                    DialogInputTextField(
                        value = inputToDoTitle,
                        onValueChange = {
                            newValue -> inputToDoTitle = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16,
                        leadingIcon = null,
                        iconSize = 18,
                        iconColor = R.color.white,
                        placeHolderText = "To Do Title"
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    Text(
                        text = "To Do Description",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.SMALL_PADDING1
                            )
                    )

                    DialogInputTextField(
                        value = inputToDoDescription,
                        onValueChange = {
                                newValue -> inputToDoDescription = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16,
                        leadingIcon = null,
                        iconSize = 18,
                        iconColor = R.color.white,
                        placeHolderText = null
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clickable {
                                onCreateDialog(
                                    CreateToDoDto(
                                        toDoItemName = inputToDoTitle,
                                        toDoItemDescription = inputToDoDescription,
                                    )
                                )
                            }
                            .padding(
                                Dimension.SMALL_PADDING2
                            )
                            .clip(
                                shape = RoundedCornerShape(
                                    10.dp
                                )
                            )
                            .background(
                                color = colorResource(
                                    R.color.excellent_end
                                )
                            )
                            .padding(
                                vertical = Dimension.SMALL_PADDING2,
                                horizontal = Dimension.MEDIUM_PADDING2
                            )
                    ) {
                        Text(
                            text = "SUBMIT",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 18.sp
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