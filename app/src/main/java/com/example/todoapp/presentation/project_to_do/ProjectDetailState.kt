package com.example.todoapp.presentation.project_to_do

import com.example.todoapp.data.dtos.CreateProjectDto

sealed class ProjectDetailState {
    data class DataState(val projectDto: CreateProjectDto) : ProjectDetailState()
    data class ErrorState(val errMsg : String) : ProjectDetailState()
    object LoadingState : ProjectDetailState()
}