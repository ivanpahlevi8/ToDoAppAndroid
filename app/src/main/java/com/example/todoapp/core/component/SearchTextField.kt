package com.example.todoapp.core.component

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.ui.theme.ToDoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    readOnly : Boolean,
    onSearch : () -> Unit,
    textInput : String,
    onChangeText : (String) -> Unit,
    navigator : NavHostController
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    navigator.navigateUp()
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_back_ic
                    ),
                    contentDescription = "Back Arrow Icon",
                    tint = colorResource(
                        id = R.color.body
                    ),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .searchBarBorder(),
                value = textInput,
                onValueChange = onChangeText,
                placeholder = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(
                            id = R.color.placeholder
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search // this will set the enter button on keyboard become search mode and trigger keyboard action when being pressed
                ),
                keyboardActions = KeyboardActions {
                    onSearch() // this function till be ran after user clicked on enter
                    focusManager.clearFocus()
                },
                leadingIcon = {
                    Icon(
                        painterResource(R.drawable.search_ic),
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .size(16.dp),
                        tint = colorResource(
                            id = R.color.body,
                        )
                    )
                },
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(
                        id = R.color.input_background,
                    ),
                    focusedTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                    unfocusedTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                    cursorColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                readOnly = readOnly,
            )
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.searchBarBorder()  = composed {
    if(!isSystemInDarkTheme()) {
        // if system in light mode, show border
        border(
            width = 2.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium,
        )
    } else {
        this
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview(){
    val controller = rememberNavController()
    ToDoAppTheme {
        SearchTextField(
            readOnly = false,
            textInput = "",
            onChangeText = {

            },
            onSearch = {},
            navigator = controller,
        )
    }
}