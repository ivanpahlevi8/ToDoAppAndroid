package com.example.todoapp.presentation.team_project.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.component.DialogInputTextField
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun CreateTeamProjectDialog(
    showDialog : Boolean,
    onDismiss : () -> Unit,
){
    // create input value for
    var inputProjectTitle by remember { mutableStateOf("") }

    var inputProjectDescription by remember { mutableStateOf("") }

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
                        text = "Add Project",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W800,
                            fontSize = 19.sp
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

                    DialogInputTextField(
                        value = inputProjectTitle,
                        onValueChange = {
                            newValue -> inputProjectTitle = newValue
                        },
                        modifier = Modifier,
                        fontSize = 16,
                        leadingIcon = null,
                        iconSize = 20,
                        iconColor = R.color.text_title,
                        placeHolderText = "Project Title",
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING2
                            )
                    )

                    Text(
                        text = "Project Description",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        ),
                        modifier = Modifier
                            .padding(
                                start = 2.dp
                            )
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.SMALL_PADDING1
                            )
                    )

                    DialogInputTextField(
                        value = inputProjectDescription,
                        onValueChange = {
                                newValue -> inputProjectDescription = newValue
                        },
                        modifier = Modifier,
                        fontSize = 16,
                        leadingIcon = null,
                        iconSize = 20,
                        iconColor = R.color.text_title,
                        placeHolderText = null,
                        singleLine = false,
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING3
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {

                            }
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.excellent_start
                                )
                            )
                            .padding(
                                vertical = Dimension.SMALL_PADDING1,
                                horizontal = Dimension.MEDIUM_PADDING2,
                            )
                    ) {
                        Text(
                            text = "INPUT",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 16.sp,
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

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateTeamProjectDialogPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.SMALL_PADDING2
                )
        ) {
            CreateTeamProjectDialog(
                showDialog = true
            ) { }
        }
    }
}