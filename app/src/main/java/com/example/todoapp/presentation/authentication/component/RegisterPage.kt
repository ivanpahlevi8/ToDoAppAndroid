package com.example.todoapp.presentation.authentication.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.RegisterUserDto
import com.example.todoapp.presentation.authentication.AuthEvent
import com.example.todoapp.presentation.authentication.AuthState
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun RegisterPage(
    onCancel : () -> Unit,
    onEvent : (AuthEvent) -> Unit,
    registerState : AuthState,
    updateRegisterState : (AuthState) -> Unit,
) {
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(
                id = R.drawable.login_background
            ),
            contentDescription = "Login Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Gray.copy(
                                alpha = 0.2F
                            ),
                            Color.Gray.copy(
                                alpha = 0.4F
                            ),
                            Color.Gray.copy(
                                alpha = 0.6F
                            )
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Dimension.SMALL_PADDING2
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W800,
                    fontSize = 22.sp,
                ),
                color = colorResource(
                    id = R.color.white
                )
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.SMALL_PADDING2
                    )
            )

            Text(
                text = "Register as a new user, enter your valid credential",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                ),
                color = colorResource(
                    id = R.color.body
                ),
                textAlign = TextAlign.Center,
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING3 * 2
                    )
            )

            TextField(
                value = usernameInput,
                onValueChange = {
                    newValue -> usernameInput = newValue
                },
                label = {
                    Text(
                        text = "Username"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            TextField(
                value = passwordInput,
                onValueChange = {
                    newValue -> passwordInput = newValue
                },
                label = {
                    Text(
                        text = "Password"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            TextField(
                value = emailInput,
                onValueChange = {
                    newValue -> emailInput = newValue
                },
                label = {
                    Text(
                        text = "Email"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            TextField(
                value = phoneNumber,
                onValueChange = {
                    newValue -> phoneNumber = newValue
                },
                label = {
                    Text(
                        text = "Phone Number"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            TextField(
                value = firstName,
                onValueChange = {
                    newValue -> firstName = newValue
                },
                label = {
                    Text(
                        text = "First Name"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            TextField(
                value = lastName,
                onValueChange = {
                    newValue -> lastName = newValue
                },
                label = {
                    Text(
                        text = "Last Name"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING1
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        onCancel()
                    },
                    colors = ButtonColors(
                        containerColor = colorResource(
                            id = R.color.average_end
                        ),
                        disabledContentColor = colorResource(
                            id = R.color.average_end
                        ),
                        contentColor = colorResource(
                            id = R.color.white
                        ),
                        disabledContainerColor = colorResource(
                            id = R.color.white
                        )
                    )
                ) {
                    Text(
                        text = "Cancel",
                        color = colorResource(
                            id = R.color.white
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(
                            Dimension.SMALL_PADDING2
                        )
                )

                Button(
                    onClick = {
                        onEvent(
                            // register
                            AuthEvent.OnRegisterEvent(
                                registerUserDto = RegisterUserDto(
                                    userName = usernameInput,
                                    password = passwordInput,
                                    email = emailInput,
                                    phoneNumber = phoneNumber,
                                    firstName = firstName,
                                    lastName = lastName,
                                )
                            )

                            // set empty
                            
                        )
                    },
                    colors = ButtonColors(
                        containerColor = colorResource(
                            id = R.color.excellent_end
                        ),
                        disabledContentColor = colorResource(
                            id = R.color.excellent_end
                        ),
                        contentColor = colorResource(
                            id = R.color.white
                        ),
                        disabledContainerColor = colorResource(
                            id = R.color.white
                        )
                    )
                ) {
                    Text(
                        text = "Register",
                        color = colorResource(
                            id = R.color.white
                        )
                    )
                }
            }
        }

        when(registerState) {
            is AuthState.LoadingState -> {
                Dialog(
                    onDismissRequest = {}
                ) {
                    Surface(
                        modifier = Modifier
                            .size(120.dp),
                        color = colorResource(
                            id = R.color.card_background_color2
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(
                                    Dimension.LARGE_PADDING2
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            is AuthState.DataState -> {
                val getData = registerState.data

                Dialog(
                    onDismissRequest = {
                        updateRegisterState(
                            AuthState.IdleState
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(
                                Dimension.MEDIUM_PADDING2
                            ),
                        color = colorResource(
                            id = R.color.excellent_end
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = Dimension.MEDIUM_PADDING2,
                                    horizontal = Dimension.LARGE_PADDING2
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Success Register As A New User",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 18.sp,
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

                            Button(
                                onClick = {
                                    updateRegisterState(
                                        AuthState.IdleState
                                    )
                                }
                            ) {
                                Text(
                                    text = "OK"
                                )
                            }
                        }
                    }
                }
            }
            is AuthState.ErrorState -> {
                Dialog(
                    onDismissRequest = {
                        updateRegisterState(
                            AuthState.IdleState
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(180.dp)
                            .padding(
                                Dimension.MEDIUM_PADDING2
                            ),
                        color = colorResource(
                            id = R.color.card_background_color2
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Success Register As A New User",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 18.sp,
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

                            Button(
                                onClick = {
                                    updateRegisterState(
                                        AuthState.IdleState
                                    )
                                }
                            ) {
                                Text(
                                    text = "OK"
                                )
                            }
                        }
                    }
                }
            }
            is AuthState.IdleState -> {}
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegisterPagePreview(){
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
            RegisterPage(
                onCancel = {},
                onEvent = {},
                registerState = AuthState.IdleState,
                updateRegisterState = {}
            )
        }
    }
}