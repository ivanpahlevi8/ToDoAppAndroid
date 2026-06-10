package com.example.todoapp.presentation.project_to_do

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.component.CustomAlertDialog
import com.example.todoapp.core.component.ErrorDialog
import com.example.todoapp.core.component.LoadingDialog
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateToDoDto
import com.example.todoapp.data.dtos.ToDoPointerDto
import com.example.todoapp.presentation.project_to_do.component.AddToDoDialog
import com.example.todoapp.presentation.project_to_do.component.ProjectInfoCard
import com.example.todoapp.presentation.project_to_do.component.ProjectItemCardShimmer
import com.example.todoapp.presentation.project_to_do.component.ToDoDetailDialog
import com.example.todoapp.presentation.project_to_do.component.ToDoPage
import com.example.todoapp.presentation.project_to_do.component.ToDoPageShimmer

@Composable
fun ToDoScreen(
    grabbedToDoList : Set<Int>,
    createdToDoList : List<ToDoPointerDto>,
    processedToDoList : List<ToDoPointerDto>,
    finishedToDoList : List<ToDoPointerDto>,
    updateToDoPosition : (ToDoPointerDto) -> Unit,
    deleteToDoPosition : (ToDoPointerDto?) -> Unit,
    onEvent : (ToDoEvent) -> Unit,
    addToDoState : ToDoState,
    updateAddToDoState : (ToDoState) -> Unit,
    updateToDoState : ToDoState,
    updateUpdateToDoState : (ToDoState) -> Unit,
    projectDetailState: ProjectDetailState,
    getAllToDoState : ToDoProjectState,
    deleteToDoState : ToDoState,
){
    var showAddToDo by remember { mutableStateOf(false) }
    var selectedToDoId by remember { mutableStateOf<ToDoPointerDto?>(null) }

    var selectedToDoDetail by remember { mutableStateOf<CreateToDoDto?>(null) }

    // create state for show and unshow delete dialog confirmation
    var showDeleteDialogConfirmation by remember { mutableStateOf(false) }

    // state for show and unshow to do detail
    var showToDoDetailDialog by remember { mutableStateOf(false) }

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
            when(projectDetailState) {
                is ProjectDetailState.DataState -> {
                    val getData = projectDetailState.projectDto

                    ProjectInfoCard(
                        project = getData
                    )
                }
                is ProjectDetailState.ErrorState -> {
                    val errMsg = projectDetailState.errMsg

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = errMsg,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = colorResource(
                                id = R.color.error_color
                            )
                        )
                    }
                }
                is ProjectDetailState.LoadingState -> {
                    ProjectItemCardShimmer()
                }
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            when(getAllToDoState){
                is ToDoProjectState.DataState<*> -> {
                    ToDoPage(
                        grabbedToDoList = grabbedToDoList,
                        onEvent = onEvent,
                        createdToDoList = createdToDoList,
                        processedToDoList = processedToDoList,
                        finishedToDoList = finishedToDoList,
                        updateToDoLocation = {
                                toDoPointerDto -> updateToDoPosition(
                                toDoPointerDto
                            )
                        },
                        onDeleteToDo = {
                            toDoId ->
                            // set selected delete id
                            selectedToDoId = toDoId

                            // show delete to do dialog confirmation
                            Log.d("CHECK", "Show dialog confirmation")
                            showDeleteDialogConfirmation = true
                        },
                        onDetailToDo = {
                            toDo ->
                            // set selected to do
                            selectedToDoDetail = toDo

                            // show to do dialog
                            showToDoDetailDialog = true
                        }
                    )
                }
                is ToDoProjectState.ErrorState -> {
                    val errMsg = getAllToDoState.errMsg

                    Text(
                        text = errMsg,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp,
                        ),
                        color = colorResource(
                            id = R.color.error_color
                        )
                    )
                }
                is ToDoProjectState.LoadingState -> {
                    ToDoPageShimmer()
                }
                is ToDoProjectState.IdleState -> {}
            }

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

            // check state for add to do
            when(addToDoState) {
                is ToDoState.LoadingState -> {
                    // show loading dialog
                    LoadingDialog()
                }
                is ToDoState.ErrorState -> {
                    // show error message
                    ErrorDialog(
                        errMsg = addToDoState.errMsg,
                        onDismiss = {}
                    )
                }
                is ToDoState.IdleState -> {
                    updateAddToDoState(
                        ToDoState.IdleState
                    )
                }
            }

            // check state for update to do
            when(updateToDoState) {
                is ToDoState.LoadingState -> {
                    // show loading dialog
                    LoadingDialog()
                }
                is ToDoState.ErrorState -> {
                    // show error message
                    ErrorDialog(
                        errMsg = updateToDoState.errMsg,
                        onDismiss = {}
                    )
                }
                is ToDoState.IdleState -> {
                    updateUpdateToDoState(
                        ToDoState.IdleState
                    )
                }
            }

            when(deleteToDoState) {
                is ToDoState.LoadingState -> {
                    // show loading dialog
                    LoadingDialog()
                }
                is ToDoState.ErrorState -> {
                    // show error message
                    ErrorDialog(
                        errMsg = deleteToDoState.errMsg,
                        onDismiss = {}
                    )
                }
                is ToDoState.IdleState -> {
                    updateUpdateToDoState(
                        ToDoState.IdleState
                    )
                }
            }


            // check state for show confirmation dialog to do
            AnimatedVisibility(
                showDeleteDialogConfirmation
            ) {
                CustomAlertDialog(
                    title = "Warning!!!",
                    content = "Are you sure want to delete to do with name ${selectedToDoId?.toDoItem?.toDoItemName}",
                    onCancel = {
                        showDeleteDialogConfirmation = false
                    },
                    onApprove = {
                        // update to do
                        deleteToDoPosition(
                            selectedToDoId
                        )

                        onEvent(
                            ToDoEvent.DeleteToDo(
                                toDoPointer = selectedToDoId
                            )
                        )

                        // un show dialog
                        showDeleteDialogConfirmation = false
                    }
                )
            }

            ToDoDetailDialog(
                toDoDto = selectedToDoDetail ?: CreateToDoDto(
                    toDoItemName = "",
                    toDoItemDescription = ""
                ),
                showDialog = showToDoDetailDialog,
                onDismiss = {
                    showToDoDetailDialog = false
                }
            )
        }
    }
}