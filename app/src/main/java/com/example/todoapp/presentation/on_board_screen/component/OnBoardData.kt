package com.example.todoapp.presentation.on_board_screen.component

import androidx.annotation.DrawableRes
import com.example.todoapp.R

data class OnBoardData(
    val title : String,
    val content : String,
    @DrawableRes val imgBackground : Int,
)

val onBoardScreenData = listOf(
    OnBoardData(
        title = "Arrange Your Project Timeline",
        content = "Project management feature can make you easy to manage timeline of your project",
        imgBackground = R.drawable.onboard_image_1
    ),
    OnBoardData(
        title = "Manage Your Team",
        content = "Manage your own team by add team member and assign project to the team",
        imgBackground = R.drawable.onboard_image_2
    ),
    OnBoardData(
        title = "Manage To Do List",
        content = "Add to do list of each project based on project and team that handle the project",
        imgBackground = R.drawable.onboard_image_3
    )
)
