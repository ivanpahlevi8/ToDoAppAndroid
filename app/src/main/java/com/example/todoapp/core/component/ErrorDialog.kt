package com.example.todoapp.core.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun ErrorDialog(
    errMsg : String,
    onDismiss : () -> Unit,
){
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Box(
                modifier = Modifier
                    .clickable {
                        onDismiss()
                    }
                    .padding(
                        vertical = Dimension.SMALL_PADDING2,
                        horizontal = Dimension.MEDIUM_PADDING1,
                    )
                    .shadow(
                        elevation = 3.dp,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .clip(
                        shape = MaterialTheme.shapes.medium,
                    )
                    .background(
                        color = colorResource(
                            id = R.color.button_delete_text
                        )
                    )
                    .padding(
                        vertical = Dimension.SMALL_PADDING2,
                        horizontal = Dimension.LARGE_PADDING1,
                    )
            ) {
                Text(
                    text = "Confirm",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W600,
                    ),
                    color = colorResource(
                        id = R.color.button_delete_background
                    )
                )
            }
        },
        title = {
            Text(
                text = "ERROR!!!",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = colorResource(
                    R.color.error_dialog_text,
                )
            )
        },
        text = {
            Text(
                text = errMsg,
                color = colorResource(
                    id = R.color.error_dialog_text
                )
            )
        },
        containerColor = colorResource(
            id = R.color.error_dialog_background,
        )
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorDialogPreview() {
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            ErrorDialog(
                errMsg = "This is mocking for error message .... Lorem Ipsum",
            ) { }
        }
    }
}