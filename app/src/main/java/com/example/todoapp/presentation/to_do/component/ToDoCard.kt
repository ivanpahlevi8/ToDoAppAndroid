package com.example.todoapp.presentation.to_do.component

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.to_do.ToDoPointerState
import com.google.gson.Gson

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToDoCard(
    ticket: CreateToDoDto,
    onDeleteConfirmed: (CreateToDoDto) -> Unit,
    grabbedToDo : Set<Int>,
    sendToSocket : (ToDoPointerDto) -> Unit,
) {
    val showDeleteTicketDialog = remember { mutableStateOf(false) }

    // 1. Check if THIS specific ticket is locked by someone else
    val isLocked = grabbedToDo.contains(ticket.toDoId)

    // 2. Change visuals based on state
    val backgroundColor = if (isLocked) Color.LightGray else Color.White
    val contentAlpha = if (isLocked) 0.5f else 1f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(backgroundColor)
            .alpha(contentAlpha)
            .then(
                if(isLocked){
                    Modifier
                } else {
                    Modifier.dragAndDropSource(block = {
                        detectTapGestures(
                            onLongPress = {
                                Log.d("CHECK", "Grabbed local on id : ${ticket.toDoId}")
                                // send state to websocket
                                sendToSocket(
                                    ToDoPointerDto(
                                        toDoPointerStatus = ToDoPointerState.Grabbed.name,
                                        toDoItem = ticket
                                    )
                                )

                                val gson = Gson()
                                val jsonString = gson.toJson(ticket)

                                startTransfer(
                                    DragAndDropTransferData(
                                        clipData = ClipData.newPlainText("toDoId", jsonString)
                                    )
                                )
                            })
                    })
                }
            ),
        shape = CardDefaults.outlinedShape,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = ticket.toDoItemName,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                if (ticket.toDoItemState == "DONE") {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                showDeleteTicketDialog.value = true
                            })
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "")
                ToDoCardContainer(
                    text = "2 days"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "")
                ToDoCardContainer(text = ticket.toDoItemName.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}