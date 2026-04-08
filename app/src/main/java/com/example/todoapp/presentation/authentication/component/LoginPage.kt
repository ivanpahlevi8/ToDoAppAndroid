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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun LoginPage(
    onRegister : () -> Unit,
){
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
                value = "",
                onValueChange = {},
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
                value = "",
                onValueChange = {},
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
                onRegister = {}
            )
        }
    }
}