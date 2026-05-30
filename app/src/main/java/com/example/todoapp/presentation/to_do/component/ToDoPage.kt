package com.example.todoapp.presentation.to_do.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.to_do.ToDoPointerState
import com.example.todoapp.presentation.to_do.ToDoStatusEnum
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun DragAndDropCompose(
    sendSocket : (ToDoPointerDto) -> Unit,
    grabbedToDoList : List<Int>,
    createdToDoList : List<ToDoPointerDto>,
    processedToDoList : List<ToDoPointerDto>,
    finishedToDoList : List<ToDoPointerDto>,
    updateToDoLocation : (ToDoPointerDto) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)

        ) {

            ToDoSpaceBox(
                modifier = Modifier.weight(1f),
                list = createdToDoList,
                sendSocket = {
                    sendSocket(it)
                },
                grabbedToDO = grabbedToDoList,
                spaceType = ToDoStatusEnum.CREATED.label,
                updateToDoLocation = updateToDoLocation
            )
            VerticalDivider()
            ToDoSpaceBox(
                modifier = Modifier.weight(1f),
                list = processedToDoList,
                sendSocket = {
                    sendSocket(it)
                },
                grabbedToDO = grabbedToDoList,
                spaceType = ToDoStatusEnum.PROCESSED.label,
                updateToDoLocation = updateToDoLocation
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DragAndDropComposePreview(){
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
            DragAndDropCompose(
                sendSocket = {},
                grabbedToDoList = listOf(),
                createdToDoList = listOf(),
                processedToDoList = listOf(),
                finishedToDoList = listOf(),
                updateToDoLocation = {}
            )
        }
    }
}