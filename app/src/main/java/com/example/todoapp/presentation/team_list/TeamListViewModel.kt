package com.example.todoapp.presentation.team_list

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.value.Constants
import com.example.todoapp.domain.models.teams.TeamModel
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.team_usecase.TeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val teamUseCase: TeamUseCase,
    private val authUseCase: AuthUseCase,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    // list team state
    private var _listTeamState by mutableStateOf<TeamListState>(TeamListState.LoadingState)

    val listTeamState : State<TeamListState> get() = derivedStateOf { _listTeamState }

    // create team state
    private var _createTeamState by mutableStateOf<TeamListState>(TeamListState.IdleState)

    val createTeamState : State<TeamListState> get() = derivedStateOf { _createTeamState }

    var userId : String = ""

    init {
        userId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""

        viewModelScope.launch {
            delay(600)

            try{
                val data = teamUseCase.getAllTeamUseCase(
                    userId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""
                )

                val newData : List<TeamModel> = data.map {
                    singleData : TeamModel ->
                    TeamModel(
                        teamId = singleData.teamId,
                        teamName = singleData.teamName,
                        teamDescription = singleData.teamDescription,
                        teamLeaderId = singleData.teamLeaderId,
                        teamLeader = authUseCase.getUserIdUseCase(
                            userId = singleData.teamLeaderId ?: ""
                        )
                    )
                }.toList()

                _listTeamState = TeamListState.DataState(
                    data = newData
                )
            } catch (e : Exception) {
                val errMsg = "Error Happen : ${e.message}"

                _listTeamState = TeamListState.ErrorState(
                    errMsg = errMsg
                )
            }
        }
    }

    fun onEvent(event : TeamListEvent) {
        when(event) {
            is TeamListEvent.OnAddTeam -> {
                _createTeamState = TeamListState.LoadingState

                viewModelScope.launch {
                    delay(500)

                    try{
                        val data = teamUseCase.createTeamUseCase(
                            createTeamDto = event.createTeamDto
                        )

                        _createTeamState = TeamListState.DataState(
                            data = data,
                        )
                    } catch (e : Exception) {
                        _createTeamState = TeamListState.ErrorState(
                            errMsg = "Error happen : ${e.message}"
                        )
                    }
                }
            }
        }
    }

    fun updateCreateTeamState(newState : TeamListState) {
        _createTeamState = newState
    }
}