package com.example.todoapp.presentation.team_project

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.project_usecase.ProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class TeamProjectViewModel @Inject constructor(
    private val projectUseCase: ProjectUseCase,
    private val authUseCase: AuthUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // create state for get project within team
    private var _getProjectTeams by mutableStateOf<TeamProjectState>(TeamProjectState.LoadingState)

    val getProjectTeamState : State<TeamProjectState> get() = derivedStateOf { _getProjectTeams }

    init {
        viewModelScope.launch {
            delay(600)

            try{
                val getData = projectUseCase.getProjectWithinTeamUseCase(
                    teamId = (savedStateHandle.get<String>("teamId") ?: "0").toInt()
                )

                // get user leader
                val userLeader : MutableList<UserModel> = mutableListOf()

                for (data in getData) {
                    val getUser = authUseCase.getUserIdUseCase(
                        userId = data.projectUserLeadId ?: ""
                    )

                    userLeader.add(getUser)
                }

                _getProjectTeams = TeamProjectState.DataState(
                    data = getData,
                    userList = userLeader
                )
            } catch (e : Exception) {
                val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                _getProjectTeams = TeamProjectState.ErrorState(
                    errMsg = errMsg
                )
            }
        }
    }
}