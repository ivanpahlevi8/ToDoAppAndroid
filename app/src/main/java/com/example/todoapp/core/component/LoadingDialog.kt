package com.example.todoapp.core.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.todoapp.R

@Composable
fun LoadingDialog(){
    AlertDialog(
        onDismissRequest = {  },
        confirmButton = {

        },
        dismissButton = { /* ... */ },
        title = {
            Text(
                text = "Loading...",
                color = colorResource(
                    id = R.color.loading_dialog_text,
                )
            )
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        },
        containerColor = colorResource(
            id = R.color.loading_dialog_background,
        ),
    )
}