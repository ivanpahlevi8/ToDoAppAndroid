package com.example.todoapp.presentation.team_project.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.core.value.Constants
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TeamProjectItemList(
    projectList : List<CreateProjectDto>,
    leadList : List<UserModel>,
    onProjectDetail : (Int) -> Unit,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(
            count = projectList.size
        ) {
            index: Int ->
            val getProject = projectList[index]
            val leader = leadList[index]

            TeamProjectItem(
                projectItem = getProject,
                userLead = leader,
                onProjectDetail = {
                    projectId -> onProjectDetail(projectId)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamProjectItemListPreview(){
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
            TeamProjectItemList(
                projectList = listOf(
                    CreateProjectDto(
                        projectName = "Test Project Name",
                        projectDescription = "Test lorem ipsum dolor sit amet. lorem ipsum dolor sit amet. lorem ipsum dolor sit amet",
                        projectCreatedAt = "2026-05-23T03:54:38.7365425",
                        projectStatus = Constants.PROJECT_STATUS_FINISHED
                    ),
                    CreateProjectDto(
                        projectName = "Test Project Name",
                        projectDescription = "Test lorem ipsum dolor sit amet. lorem ipsum dolor sit amet. lorem ipsum dolor sit amet",
                        projectCreatedAt = "2026-05-23T03:54:38.7365425",
                        projectStatus = Constants.PROJECT_STATUS_FINISHED
                    ),
                    CreateProjectDto(
                        projectName = "Test Project Name",
                        projectDescription = "Test lorem ipsum dolor sit amet. lorem ipsum dolor sit amet. lorem ipsum dolor sit amet",
                        projectCreatedAt = "2026-05-23T03:54:38.7365425",
                        projectStatus = Constants.PROJECT_STATUS_FINISHED
                    ),
                    CreateProjectDto(
                        projectName = "Test Project Name",
                        projectDescription = "Test lorem ipsum dolor sit amet. lorem ipsum dolor sit amet. lorem ipsum dolor sit amet",
                        projectCreatedAt = "2026-05-23T03:54:38.7365425",
                        projectStatus = Constants.PROJECT_STATUS_FINISHED
                    ),
                ),
                leadList = listOf(
                    UserModel(
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "",
                        userFirstName = "",
                        userCreatedAt = "",
                        userLastName = "",
                        userPhoneNumber = ""
                    ),
                    UserModel(
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "",
                        userFirstName = "",
                        userCreatedAt = "",
                        userLastName = "",
                        userPhoneNumber = ""
                    ),
                    UserModel(
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "",
                        userFirstName = "",
                        userCreatedAt = "",
                        userLastName = "",
                        userPhoneNumber = ""
                    ),
                    UserModel(
                        userId = "",
                        userName = "ivanpahlevi8",
                        userEmail = "",
                        userFirstName = "",
                        userCreatedAt = "",
                        userLastName = "",
                        userPhoneNumber = ""
                    ),
                )
            ) { }
        }
    }
}