package com.example.todoapp.presentation.project_to_do.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.project_to_do.ToDoEvent
import com.example.todoapp.presentation.project_to_do.ToDoStatusEnum
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun ToDoPage(
    onEvent : (ToDoEvent) -> Unit,
    grabbedToDoList : Set<Int>,
    createdToDoList : List<ToDoPointerDto>,
    processedToDoList : List<ToDoPointerDto>,
    finishedToDoList : List<ToDoPointerDto>,
    updateToDoLocation : (ToDoPointerDto) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .horizontalScroll(
                    rememberScrollState()
                )
                .fillMaxSize()
                .padding(8.dp)

        ) {

            ToDoSpaceBox(
                modifier = Modifier,
                list = createdToDoList,
                grabbedToDO = grabbedToDoList,
                spaceType = ToDoStatusEnum.CREATED.label,
                updateToDoLocation = updateToDoLocation,
                onGrabbedItem = {
                    item -> onEvent(
                        ToDoEvent.OnGrabbedItem(item)
                    )
                },
                updateToDoDb = {
                    onEvent(
                        ToDoEvent.UpdateToDo(
                            it
                        )
                    )
                },
                colorId = R.color.time_line_card_color4
            )
            VerticalDivider()
            ToDoSpaceBox(
                modifier = Modifier,
                list = processedToDoList,
                grabbedToDO = grabbedToDoList,
                spaceType = ToDoStatusEnum.PROCESSED.label,
                updateToDoLocation = updateToDoLocation,
                onGrabbedItem = {
                        item -> onEvent(
                    ToDoEvent.OnGrabbedItem(item)
                )
                },
                updateToDoDb = {
                    onEvent(
                        ToDoEvent.UpdateToDo(
                            it
                        )
                    )
                },
                colorId = R.color.time_line_card_color5
            )
            VerticalDivider()
            ToDoSpaceBox(
                modifier = Modifier,
                list = finishedToDoList,
                grabbedToDO = grabbedToDoList,
                spaceType = ToDoStatusEnum.FINISHED.label,
                updateToDoLocation = updateToDoLocation,
                onGrabbedItem = {
                        item -> onEvent(
                    ToDoEvent.OnGrabbedItem(item)
                )
                },
                updateToDoDb = {
                    onEvent(
                        ToDoEvent.UpdateToDo(
                            it
                        )
                    )
                },
                colorId = R.color.time_line_card_color6
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
            ToDoPage(
                grabbedToDoList = setOf(),
                createdToDoList = listOf(),
                processedToDoList = listOf(),
                finishedToDoList = listOf(),
                updateToDoLocation = {},
                onEvent = {}
            )
        }
    }
}