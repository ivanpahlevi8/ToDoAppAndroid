package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.component.SearchTextField
import com.example.todoapp.core.component.shimmerEffect
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.presentation.team_detail.TeamDetailState
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun AddTeamMemberDialog(
    showDialog : Boolean,
    onDismiss : () -> Unit,
    onSearchConnection : (String) -> Unit,
    searchConnectionState : TeamDetailState,
){
    var searchNameInput by remember { mutableStateOf("") }

    AnimatedVisibility(
        visible = showDialog
    ) {
        Dialog(
            onDismissRequest = onDismiss,
        ) {
            Surface(
                color = colorResource(
                    id = R.color.card_information_background1
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = Dimension.SMALL_PADDING2,
                            horizontal = Dimension.MEDIUM_PADDING1
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Add Member To Team",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            fontSize = 22.sp
                        ),
                        color = colorResource(
                            id = R.color.white
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    SearchTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Dimension.SMALL_PADDING2
                            ),
                        readOnly = false,
                        onSearch = {
                            onSearchConnection(searchNameInput)
                        },
                        textInput = searchNameInput,
                        onChangeText = {
                            newValue : String -> searchNameInput = newValue
                        },
                        navigator = null
                    )

                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.MEDIUM_PADDING1
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(
                                horizontal = Dimension.SMALL_PADDING2
                            ),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        when(searchConnectionState) {
                            is TeamDetailState.DataState<*> -> {
                                // get data
                                val getData = searchConnectionState.data as List<UserModel>

                                // check if data exist or not
                                if(getData.isEmpty()){
                                    Text(
                                        text = "No User Match",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        ),
                                        color = colorResource(
                                            id = R.color.text_title
                                        )
                                    )
                                } else {
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(
                                                horizontal = Dimension.MEDIUM_PADDING1
                                            ),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        items(
                                            count = getData.size
                                        ){
                                            index: Int ->
                                            val getConnection = getData[index]

                                            AddTeamMemberItem(
                                                user = getConnection
                                            )
                                        }
                                    }
                                }
                            }
                            is TeamDetailState.ErrorState -> {
                                val errMsg = searchConnectionState.errMsg

                                Text(
                                    text = errMsg,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W700,
                                        fontSize = 16.sp
                                    ),
                                    color = colorResource(
                                        id = R.color.error_color
                                    )
                                )
                            }
                            is TeamDetailState.LoadingState -> {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            horizontal = Dimension.MEDIUM_PADDING1
                                        ),
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    items(
                                        count = 5
                                    ){
                                        AddTeamMemberItemShimmer()
                                    }
                                }
                            }
                            is TeamDetailState.IdleState -> {

                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddTeamMemberDialogPreview(){
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
            AddTeamMemberDialog(
                showDialog = true,
                onDismiss = {},
                searchConnectionState = TeamDetailState.LoadingState,
                onSearchConnection = {}
            )
        }
    }
}

@Composable
private fun AddTeamMemberItem(
    user : UserModel
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING1
            )
            .shadow(
                elevation = 2.dp,
                shape = MaterialTheme.shapes.medium
            )
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = colorResource(
                    id = R.color.time_line_card_color6
                )
            )
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
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
                contentScale = ContentScale.Crop,
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(50.dp)
            )

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${user.userFirstName} ${user.userLastName}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 18.sp
                    ),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.email_ic
                        ),
                        contentDescription = "",
                        tint = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier
                            .size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Text(
                        text = user.userEmail,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.text_format_ic
                        ),
                        contentDescription = "",
                        tint = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier
                            .size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Text(
                        text = user.userName,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Box(
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple()
                    ) {

                    }
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        color = colorResource(
                            id = R.color.success_dialog_background
                        )
                    )
                    .padding(
                        Dimension.SMALL_PADDING2
                    )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_ic
                    ),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(20.dp),
                    tint = colorResource(
                        id = R.color.black
                    )
                )
            }
        }
    }
}

@Composable
fun AddTeamMemberItemShimmer(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING1
            )
            .shadow(
                elevation = 2.dp,
                shape = MaterialTheme.shapes.medium
            )
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = colorResource(
                    id = R.color.time_line_card_color6
                )
            )
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
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
                contentScale = ContentScale.Crop,
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(50.dp)
            )

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(120.dp)
                        .clip(
                            shape = MaterialTheme.shapes.small
                        )
                        .shimmerEffect()
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.email_ic
                        ),
                        contentDescription = "",
                        tint = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier
                            .size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .height(15.dp)
                            .width(100.dp)
                            .clip(
                                shape = MaterialTheme.shapes.small
                            )
                            .shimmerEffect()
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.text_format_ic
                        ),
                        contentDescription = "",
                        tint = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier
                            .size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .height(15.dp)
                            .width(100.dp)
                            .clip(
                                shape = MaterialTheme.shapes.small
                            )
                            .shimmerEffect()
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Box(
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple()
                    ) {

                    }
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        color = colorResource(
                            id = R.color.success_dialog_background
                        )
                    )
                    .padding(
                        Dimension.SMALL_PADDING2
                    )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_ic
                    ),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(20.dp),
                    tint = colorResource(
                        id = R.color.black
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddTeamMemberItemPreview(){
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
            AddTeamMemberItem(
                user = UserModel(
                    userId = "",
                    userEmail = "test@test.com",
                    userName = "testusername",
                    userCreatedAt = "",
                    userFirstName = "Ivan",
                    userPhoneNumber = "",
                    userLastName = "Pahlevi"
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddTeamMemberItemShimmerPreview(){
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
            AddTeamMemberItemShimmer()
        }
    }
}