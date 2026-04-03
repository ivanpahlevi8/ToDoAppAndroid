package com.example.todoapp.core.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun ErrorPage(
    errorMsg : String,
){
    Column(
        modifier = Modifier
            .padding(
                horizontal = Dimension.MEDIUM_PADDING1,
                vertical = Dimension.SMALL_PADDING2,
            )
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Error Happen!!!",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(
                    id = R.color.error_color
                )
            )
        }

        Spacer(
            modifier = Modifier
                .height(Dimension.MEDIUM_PADDING2)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = errorMsg,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                ),
                color = colorResource(
                    id = R.color.error_color,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorPagePreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            ErrorPage(
                errorMsg = "This is error message for mocking only, not real error message!!!"
            )
        }
    }
}