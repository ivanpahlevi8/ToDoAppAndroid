package com.example.todoapp.presentation.authentication.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.core.content.ContextCompat.getDrawable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.todoapp.data.dtos.LoginUserDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.presentation.authentication.AuthEvent
import com.example.todoapp.presentation.authentication.AuthState
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun LoginPage(
    onRegister : () -> Unit,
    onEvent : (AuthEvent) -> Unit,
    loginState : AuthState,
    onUpdateLoginState : (AuthState) -> Unit,
    setUserLogIn : () -> Unit,
){
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(
                id = R.drawable.login_background
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Dimension.MEDIUM_PADDING1
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "To Do App",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W900,
                    fontSize = 24.sp,
                ),
                color = colorResource(
                    id = R.color.white,
                )
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING2
                    )
            )

            Text(
                text = "Use you previous credential to log in",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = 18.sp,
                ),
                color = colorResource(
                    id = R.color.white,
                )
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.LARGE_PADDING3 * 2
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
                        Dimension.MEDIUM_PADDING2
                    )
            )

            Button(
                onClick = {
                    onEvent(
                        AuthEvent.OnLoginEvent(
                            loginUserDto = LoginUserDto(
                                username = usernameInput,
                                password = passwordInput
                            )
                        )
                    )

                    usernameInput = ""
                    passwordInput = ""
                },
                colors = ButtonColors(
                    containerColor = colorResource(
                        id = R.color.excellent_end
                    ),
                    contentColor = colorResource(
                        id = R.color.white
                    ),
                    disabledContainerColor = colorResource(
                        id = R.color.excellent_end
                    ),
                    disabledContentColor = colorResource(
                        id = R.color.white,
                    )
                )
            ) {
                Text(
                    text = "Login"
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "does not have account?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 14.sp,
                    ),
                    color = colorResource(
                        id = R.color.white
                    ),
                )
                Spacer(
                    modifier = Modifier
                        .width(
                            Dimension.SMALL_PADDING2
                        )
                )
                Text(
                    text = "create account",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 14.sp,
                    ),
                    color = colorResource(
                        id = R.color.body
                    ),
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            onRegister()
                        }
                )
            }
        }

        when(loginState) {
            is AuthState.LoadingState -> {
                Dialog(
                    onDismissRequest = {}
                ) {
                    Surface(
                        color = colorResource(
                            id = R.color.card_information_background1
                        ),
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .size(100.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(
                                    Dimension.MEDIUM_PADDING3
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            CircularProgressIndicator(
                                color = Color.White
                            )
                        }
                    }
                }
            }
            is AuthState.DataState -> {
                val data = loginState.data

                Dialog(
                    onDismissRequest = {
                        onUpdateLoginState(
                            AuthState.IdleState
                        )

                        setUserLogIn()
                    }
                ) {
                    Surface(
                        color = colorResource(
                            id = R.color.card_information_background1
                        ),
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .width(180.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(
                                    Dimension.MEDIUM_PADDING3
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                modifier = Modifier.clip(CircleShape).size(100.dp),
                                painter = rememberDrawablePainter(
                                    drawable = getDrawable(
                                        LocalContext.current,
                                        R.drawable.check_ok
                                    ),
                                ),
                                contentDescription = "Loading animation",
                                contentScale = ContentScale.FillWidth,
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING1
                                    )
                            )

                            Text(
                                text = "Success login as ${data.userName}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 14.sp,
                                ),
                                color = colorResource(
                                    id = R.color.text_title,
                                ),
                                textAlign = TextAlign.Center,
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.SMALL_PADDING2,
                                    )
                            )

                            Button(
                                onClick = {
                                    onUpdateLoginState(
                                        AuthState.IdleState
                                    )

                                    setUserLogIn()
                                }
                            ) {
                                Text(
                                    text = "OK",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                    ),
                                    color = colorResource(
                                        id = R.color.text_title
                                    )
                                )
                            }
                        }
                    }
                }
            }
            is AuthState.ErrorState -> {
                val errMsg = loginState.errMsg

                Dialog(
                    onDismissRequest = {
                        onUpdateLoginState(
                            AuthState.IdleState
                        )
                    }
                ) {
                    Surface(
                        color = colorResource(
                            id = R.color.card_information_background1
                        ),
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .width(180.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(
                                    Dimension.MEDIUM_PADDING3
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                modifier = Modifier.clip(CircleShape).size(100.dp),
                                painter = rememberDrawablePainter(
                                    drawable = getDrawable(
                                        LocalContext.current,
                                        R.drawable.x_error
                                    ),
                                ),
                                contentDescription = "Loading animation",
                                contentScale = ContentScale.FillWidth,
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.MEDIUM_PADDING1
                                    )
                            )

                            Text(
                                text = errMsg,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 14.sp,
                                ),
                                color = colorResource(
                                    id = R.color.text_title,
                                ),
                                textAlign = TextAlign.Center,
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(
                                        Dimension.SMALL_PADDING2,
                                    )
                            )

                            Button(
                                onClick = {
                                    onUpdateLoginState(
                                        AuthState.IdleState
                                    )
                                },
                                colors = ButtonColors(
                                    containerColor = colorResource(
                                        id = R.color.error_color
                                    ),
                                    disabledContentColor = colorResource(
                                        id = R.color.white
                                    ),
                                    contentColor = colorResource(
                                        id = R.color.white
                                    ),
                                    disabledContainerColor = colorResource(
                                        id = R.color.error_color
                                    )
                                )
                            ) {
                                Text(
                                    text = "OK",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                    ),
                                    color = colorResource(
                                        id = R.color.text_title
                                    )
                                )
                            }
                        }
                    }
                }
            }
            is AuthState.IdleState -> {

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginPagePreview() {
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.SMALL_PADDING1
                )
        ) {
            LoginPage(
                onRegister = {},
                onEvent = {},
                loginState = AuthState.DataState(
                    data = UserModel(
                        userFirstName = "",
                        userLastName = "",
                        userCreatedAt = "",
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "",
                        userPhoneNumber = ""
                    )
                ),
                onUpdateLoginState = {},
                setUserLogIn = {}
            )
        }
    }
}