package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
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
fun CreateTeamRoleDialog(showDialog : Boolean, onDismiss : ()->Unit, onAddRole : (String) -> Unit){
    // create input
    var roleNameInput by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    if(showDialog){
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Surface(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    },
                shape = MaterialTheme.shapes.medium,
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Add New Role",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    DialogInputTextField(
                        value = roleNameInput,
                        onValueChange = {
                                newValue : String -> roleNameInput = newValue
                        },
                        modifier = Modifier,
                        fontSize = 16,
                        leadingIcon = R.drawable.text_format_ic,
                        iconSize = 22,
                        iconColor = R.color.text_title,
                        placeHolderText = "Input Role Name"
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable{
                                onAddRole(roleNameInput)
                            }
                            .padding(
                                Dimension.SMALL_PADDING2
                            )
                            .shadow(
                                elevation = 3.dp,
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
                                vertical = Dimension.SMALL_PADDING2,
                                horizontal = Dimension.MEDIUM_PADDING2
                            )
                    ) {
                        Text(
                            text = "INSERT",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 18.sp,
                            ),
                            color = colorResource(
                                id = R.color.black  ,
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
fun CreateTeamRoleDialogPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.SMALL_PADDING2
                )
        ) {
            CreateTeamRoleDialog(
                showDialog = true,
                onAddRole = {},
                onDismiss = {}
            )
        }
    }
}