package com.example.todoapp.presentation.project_to_do.component

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.project_to_do.ToDoPointerState
import com.google.gson.Gson

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToDoCard(
    toDo: CreateToDoDto,
    grabbedToDo : Set<Int>,
    sendToSocket : (ToDoPointerDto) -> Unit,
    onDeleteToDo : (ToDoPointerDto) -> Unit,
) {
    val showDeleteTicketDialog = remember { mutableStateOf(false) }

    // 1. Check if THIS specific ticket is locked by someone else
    val isLocked = grabbedToDo.contains(toDo.toDoId)

    // 2. Change visuals based on state
    val backgroundColor = if (isLocked) Color.LightGray else Color.White
    val contentAlpha = if (isLocked) 0.5f else 1f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .background(backgroundColor)
            .alpha(contentAlpha)
            .then(
                if(isLocked){
                    Modifier
                } else {
                    Modifier.dragAndDropSource(block = {
                        detectTapGestures(
                            onLongPress = {
                                // send state to websocket
                                sendToSocket(
                                    ToDoPointerDto(
                                        toDoPointerStatus = ToDoPointerState.Grabbed.name,
                                        toDoItem = toDo
                                    )
                                )

                                val gson = Gson()
                                val jsonString = gson.toJson(toDo)

                                startTransfer(
                                    DragAndDropTransferData(
                                        clipData = ClipData.newPlainText("toDoId", jsonString)
                                    )
                                )
                            })
                    })
                }
            ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Column(
                modifier = Modifier
                    .width(
                        IntrinsicSize.Max
                    )
            ) {
                Text(
                    text = toDo.toDoItemName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W900
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                HorizontalDivider(
                    thickness = 1.5.dp,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Text(
                text = toDo.toDoItemDescription,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp
                ),
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING2
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // delete button
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            // on delete
                            onDeleteToDo(
                                ToDoPointerDto(
                                    toDoPointerStatus = ToDoPointerState.Deleted.name,
                                    toDoItem = toDo
                                )
                            )
                        }
                        .padding(
                            2.dp
                        )
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = colorResource(
                                id = R.color.error_color
                            )
                        )
                        .padding(
                            vertical = 2.dp,
                            horizontal = Dimension.SMALL_PADDING2
                        )
                ) {
                    Text(
                        text = "DELETE",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W800,
                            fontSize = 12.sp
                        ),
                        color = colorResource(
                            id = R.color.white
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                // detail button
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            // on detail
                        }
                        .padding(
                            2.dp
                        )
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = colorResource(
                                id = R.color.average_end
                            )
                        )
                        .padding(
                            vertical = 2.dp,
                            horizontal = Dimension.SMALL_PADDING2
                        )
                ) {
                    Text(
                        text = "DETAIL",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W800,
                            fontSize = 12.sp
                        ),
                        color = colorResource(
                            id = R.color.white
                        )
                    )
                }
            }
        }

    }
}