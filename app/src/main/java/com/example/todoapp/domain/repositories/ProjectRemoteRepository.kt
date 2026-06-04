package com.example.todoapp.domain.repositories

import com.example.todoapp.data.dtos.CreateProjectDto

interface ProjectRemoteRepository {
    // function to create project within team
    suspend fun createProjectWithinTeam(createProjectDto: CreateProjectDto) : String

    // function to get all project within team
    suspend fun getAllProjectWithinTeam(teamId : Int) : List<CreateProjectDto>

    // function to get detail project
    suspend fun getDetailProject(projectId : Int) : CreateProjectDto
}