package com.example.todoapp.presentation.team_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.usecase.team_role_usecase.TeamRoleUseCase
import com.example.todoapp.domain.usecase.team_usecase.TeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val teamUseCase: TeamUseCase,
    private val teamRoleUseCase: TeamRoleUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // create state for team detail
    private var _teamDetailState by mutableStateOf<TeamDetailState>(TeamDetailState.LoadingState)

    val teamDetailState : State<TeamDetailState> get() = derivedStateOf { _teamDetailState }

    init {
        viewModelScope.launch {
            delay(600)

            try{
                // get team detail
                val data = teamUseCase.getTeamUseCase(
                    teamId = (savedStateHandle.get<String>("teamId") ?: "0").toInt()
                )

                // get role model
                val getTeamRole = teamRoleUseCase.getAllTeamRoleUseCase(
                    teamId = (savedStateHandle.get<String>("teamId") ?: "0").toInt()
                )

                _teamDetailState = TeamDetailState.DataState(
                    data = data,
                    roleModel = getTeamRole
                )
            } catch (e : Exception) {
                val errMsg = "Error Happen : ${e.message}"

                _teamDetailState = TeamDetailState.ErrorState(
                    errMsg = errMsg
                )
            }
        }
    }
}