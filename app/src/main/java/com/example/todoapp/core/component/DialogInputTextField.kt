package com.example.todoapp.core.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun DialogInputTextField(
    value : String,
    onValueChange : (String) -> Unit,
    modifier: Modifier,
    fontSize : Int,
    @DrawableRes leadingIcon : Int?,
    iconSize : Int,
    iconColor: Int,
    placeHolderText : String?,
    singleLine : Boolean = true,
){
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focucManager = LocalFocusManager.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.small,
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(
                        id = R.color.text_title
                    )
                ),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth(),
        singleLine = singleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize.sp
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done // Overrides the "New Line" button with a "Done" button
        ),
        keyboardActions = KeyboardActions {
            focucManager.clearFocus();
        },
        decorationBox = { innerTextField ->
            Row(
                modifier.padding(
                    vertical = Dimension.MEDIUM_PADDING1,
                    horizontal = Dimension.SMALL_PADDING2,
                ),
                verticalAlignment = Alignment.Bottom
            ) {
                if (leadingIcon != null) {
                    Icon(
                        painter = painterResource(
                            id = leadingIcon
                        ),
                        contentDescription = "Leading Icon Desc",
                        modifier = Modifier
                            .size(iconSize.dp),
                        tint = colorResource(
                            id = iconColor
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )
                }

                Box(Modifier.weight(1f)) {
                    if (placeHolderText != null) {
                        if(isFocused) {
                            Column(
                                modifier = Modifier
                                    .width(
                                        IntrinsicSize.Max
                                    )
                                    .padding(
                                        bottom = 25.dp,
                                    ),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = placeHolderText,
                                    style = LocalTextStyle.current.copy(
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                        fontSize = fontSize.sp
                                    )
                                )
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    thickness = 1.dp
                                )
                            }
                        } else {
                            if(value == "") {
                                Text(
                                    text = placeHolderText,
                                    style = LocalTextStyle.current.copy(
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                        fontSize = fontSize.sp
                                    )
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(
                                alignment = Alignment.BottomStart
                            )
                    ) {
                        innerTextField()
                    }
                }
/*                if (trailingIcon != null) trailingIcon()*/
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DialogInputTextFieldPreview(){
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
            DialogInputTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier,
                fontSize = 16,
                leadingIcon = R.drawable.text_format_ic,
                iconSize = 20,
                iconColor = R.color.text_title,
                placeHolderText = "Input Name"
            )
        }
    }
}