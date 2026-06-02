package com.example.todoapp.presentation.to_do

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.to_do.component.AddToDoDialog
import com.example.todoapp.presentation.to_do.component.DragAndDropCompose

@Composable
fun ToDoScreen(
    onClick : (ToDoPointerDto) -> Unit,
    grabbedToDoList : Set<Int>,
    createdToDoList : List<ToDoPointerDto>,
    processedToDoList : List<ToDoPointerDto>,
    finishedToDoList : List<ToDoPointerDto>,
    updateToDoLocation : (ToDoPointerDto) -> Unit,
    onEvent : (ToDoEvent) -> Unit,
){
    var showAddToDo by remember { mutableStateOf(false) }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddToDo = true
                },
                shape = RoundedCornerShape(
                    16.dp
                ),
                containerColor = colorResource(
                    id = R.color.excellent_end
                )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_ic
                    ),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(22.dp),
                    tint = colorResource(
                        id = R.color.white
                    )
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    it
                )
        ) {
            DragAndDropCompose(
                grabbedToDoList = grabbedToDoList,
                sendSocket = {
                        item : ToDoPointerDto ->  onClick(item)
                },
                createdToDoList = createdToDoList,
                processedToDoList = processedToDoList,
                finishedToDoList = finishedToDoList,
                updateToDoLocation = updateToDoLocation
            )

            AddToDoDialog(
                showDialog = showAddToDo,
                onDismiss = {
                    showAddToDo = false
                },
                onCreateDialog = {
                    item -> onEvent(
                        ToDoEvent.CreateToDo(
                            item
                        )
                    )
                }
            )
        }
    }
}