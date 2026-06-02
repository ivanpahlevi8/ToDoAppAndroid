package com.example.todoapp.presentation.to_do.component

import android.content.ClipDescription
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.to_do.ToDoPointerState
import com.google.gson.Gson
import androidx.compose.foundation.lazy.items

@Composable
fun ToDoSpaceBox(
    modifier: Modifier,
    list: List<ToDoPointerDto>,
    grabbedToDO : Set<Int>,
    sendSocket : (ToDoPointerDto) -> Unit,
    spaceType : String,
    updateToDoLocation : (ToDoPointerDto) -> Unit,
){
    var backgroundColor by remember { mutableStateOf(Color(0xffE5E4E2)) }
    val titleStyle = remember { mutableStateOf(FontWeight.Normal) }
    val scale by animateFloatAsState(
        if (titleStyle.value == FontWeight.Bold) 1.4f else 1f,
        label = "scale"
    )
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val data = event.toAndroidDragEvent().clipData.getItemAt(0).text.toString()

                val gson = Gson()
                val droppedTicket = gson.fromJson(data, CreateToDoDto::class.java)

                val toDoPointer = ToDoPointerDto(
                    toDoPointerStatus = ToDoPointerState.Dropped.name,
                    toDoItem = droppedTicket,
                    targetToDoState = spaceType,
                )

                // update state to drop
                sendSocket(
                    toDoPointer
                )

                // update local position
                updateToDoLocation(
                    toDoPointer
                )

                return true
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                Log.d("CHECK", "On entered")
                backgroundColor = Color(0xffD3D3D3)
                titleStyle.value = FontWeight.Bold
            }

            override fun onExited(event: DragAndDropEvent) {
                super.onExited(event)
                Log.d("CHECK", "On exited")
                backgroundColor = Color(0xffE5E4E2)
                titleStyle.value = FontWeight.Normal
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = backgroundColor)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event
                        .mimeTypes()
                        .contains(ClipDescription.MIMETYPE_TEXT_PLAIN) // <--- Changed this!
                },
                target = dragAndDropTarget
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            stickyHeader {
                Text(
                    text = "Test",
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin.Center
                        },
                    textAlign = TextAlign.Center,
                    fontWeight = titleStyle.value,
                )
            }

            items(
                items = list,
                // 1. Provide a unique key based on the ticket's ID!
                key = { item -> item.toDoItem.toDoId ?: 0 }
            ) { item ->
                if (list.isNotEmpty()) {
                    ToDoCard(
                        ticket = item.toDoItem,
                        onDeleteConfirmed = {},
                        sendToSocket = sendSocket,
                        grabbedToDo = grabbedToDO
                    )
                }
            }
        }
    }
}