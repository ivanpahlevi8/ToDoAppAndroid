package com.example.todoapp.presentation.team_detail

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
    private val authUseCase: AuthUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // create state for team detail
    private var _teamDetailState by mutableStateOf<TeamDetailState>(TeamDetailState.LoadingState)

    val teamDetailState : State<TeamDetailState> get() = derivedStateOf { _teamDetailState }

    // create state for create team role
    private var _createTeamRoleState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val createTeamRoleState : State<TeamDetailState> get() = derivedStateOf { _createTeamRoleState }

    // create state for delete team role
    private var _deleteTeamRoleState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val deleteTeamRoleState : State<TeamDetailState> get() = derivedStateOf { _deleteTeamRoleState }

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

                // get team leader data
                val getTeamLeader : UserModel = authUseCase.getUserIdUseCase(
                    userId = data.teamLeaderId ?: ""
                )

                // update team leader on data
                data.teamLeader = getTeamLeader

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

    fun onEvent(event : TeamDetailEvent) {
        when(event) {
            is TeamDetailEvent.OnDeleteTeamRole -> {
                // update delete state
                _deleteTeamRoleState = TeamDetailState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        // get role id
                        val getRoleId = event.teamRoleId

                        // remove all user from with with this role
                        
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                        _deleteTeamRoleState = TeamDetailState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is TeamDetailEvent.OnAddTeamRole -> {

            }
        }
    }
}