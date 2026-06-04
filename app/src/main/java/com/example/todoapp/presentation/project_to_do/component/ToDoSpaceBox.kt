package com.example.todoapp.presentation.project_to_do.component

import android.content.ClipDescription
import androidx.annotation.ColorRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.project_to_do.ToDoPointerState
import com.google.gson.Gson
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.presentation.project_to_do.ToDoStatusEnum

@Composable
fun ToDoSpaceBox(
    modifier: Modifier,
    list: List<ToDoPointerDto>,
    grabbedToDO : Set<Int>,
    onGrabbedItem : (ToDoPointerDto) -> Unit,
    spaceType : String,
    updateToDoLocation : (ToDoPointerDto) -> Unit,
    updateToDoDb : (ToDoPointerDto) -> Unit,
    @ColorRes colorId : Int,
){
    val titleStyle = remember { mutableStateOf(FontWeight.Normal) }
    val scale by animateFloatAsState(
        if (titleStyle.value == FontWeight.Bold) 1.3f else 1f,
        label = "scale"
    )
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                // set title to normal again
                titleStyle.value = FontWeight.Normal

                val data = event.toAndroidDragEvent().clipData.getItemAt(0).text.toString()

                val gson = Gson()
                val droppedTicket = gson.fromJson(data, CreateToDoDto::class.java)

                val toDoPointer = ToDoPointerDto(
                    toDoPointerStatus = ToDoPointerState.Dropped.name,
                    toDoItem = droppedTicket,
                    targetToDoState = spaceType,
                )

                // update local position
                updateToDoLocation(
                    toDoPointer
                )

                // update on db
                updateToDoDb(
                    toDoPointer
                )

                return true
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                titleStyle.value = FontWeight.Bold
            }

            override fun onExited(event: DragAndDropEvent) {
                super.onExited(event)
                titleStyle.value = FontWeight.Normal
            }
        }
    }

    Box(
        modifier = modifier
            .width(
                LocalConfiguration.current.screenWidthDp.dp/2,
            )
            .fillMaxHeight()
            .padding(
                horizontal = 8.dp
            )
            .clip(
                shape = RoundedCornerShape(
                    6.dp
                )
            )
            .background(color = colorResource(
                id = colorId
            ))
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
                    text = when(spaceType){
                        ToDoStatusEnum.CREATED.label -> {
                            "Created"
                        }
                        ToDoStatusEnum.PROCESSED.label -> {
                            "Processed"
                        }
                        ToDoStatusEnum.FINISHED.label -> {
                            "Finished"
                        }
                        else -> {
                            ""
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin.Center
                        },
                    textAlign = TextAlign.Center,
                    fontWeight = titleStyle.value,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING2
                        )
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
                        sendToSocket = onGrabbedItem,
                        grabbedToDo = grabbedToDO
                    )
                }
            }
        }
    }
}