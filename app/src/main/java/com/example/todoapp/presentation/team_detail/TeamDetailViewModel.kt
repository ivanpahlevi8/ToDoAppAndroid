package com.example.todoapp.presentation.team_detail

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.core.value.Constants
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.domain.usecase.authorization_usecase.AuthUseCase
import com.example.todoapp.domain.usecase.team_role_usecase.TeamRoleUseCase
import com.example.todoapp.domain.usecase.team_usecase.TeamUseCase
import com.example.todoapp.domain.usecase.user_connection_usecase.UserConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val teamUseCase: TeamUseCase,
    private val teamRoleUseCase: TeamRoleUseCase,
    private val authUseCase: AuthUseCase,
    private val userConnectionUseCase: UserConnectionUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    // create state for team detail
    private var _teamDetailState by mutableStateOf<TeamDetailState>(TeamDetailState.LoadingState)

    val teamDetailState : State<TeamDetailState> get() = derivedStateOf { _teamDetailState }

    // create state for role on team
    private var _roleOnTeamState by mutableStateOf<TeamDetailState>(TeamDetailState.LoadingState)

    val roleOnTeamState : State<TeamDetailState> get() = derivedStateOf { _roleOnTeamState }

    // create state for create team role
    private var _createTeamRoleState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val createTeamRoleState : State<TeamDetailState> get() = derivedStateOf { _createTeamRoleState }

    // create state for delete team role
    private var _deleteTeamRoleState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val deleteTeamRoleState : State<TeamDetailState> get() = derivedStateOf { _deleteTeamRoleState }

    // create state for search connection
    private var _searchConnectionState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val searchConnectionState : State<TeamDetailState> get() = derivedStateOf { _searchConnectionState }

    // create state to add member to the team
    private var _addTeamMemberState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val addTeamMemberState : State<TeamDetailState> get() = derivedStateOf { _addTeamMemberState }

    // create state to remove team member
    private var _removeTeamMemberState by mutableStateOf<TeamDetailState>(TeamDetailState.IdleState)

    val removeTeamMemberState : State<TeamDetailState> get() = derivedStateOf { _removeTeamMemberState }

    // create variable for check weather the login user is team leader or not
    var isTeamLeader by mutableStateOf(false)

    // variable to hold login user id value
    var loginUserId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""

    init {
        viewModelScope.launch {
            delay(600)

            // get team header data
            getTeamHeaderData()

            // add delay
            delay(400)

            // get role on the team
            getAllRoleTeam()
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

                        // delete role
                        val getRole = teamRoleUseCase.deleteTeamRoleUseCase(
                            teamRoleId = getRoleId
                        )

                        _deleteTeamRoleState = TeamDetailState.DataState(
                            data = getRole,
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                        _deleteTeamRoleState = TeamDetailState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is TeamDetailEvent.OnAddTeamRole -> {
                // update delete state
                _createTeamRoleState = TeamDetailState.LoadingState

                viewModelScope.launch {
                    delay(600)

                    try{
                        // get role id
                        val getRole = event.teamRoleDto

                        // delete role
                        val getData = teamRoleUseCase.createTeamRoleUseCase(
                            teamRoleDto = getRole
                        )

                        _createTeamRoleState = TeamDetailState.DataState(
                            data = getData,
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                        _deleteTeamRoleState = TeamDetailState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is TeamDetailEvent.OnAddTeamMember -> {
                viewModelScope.launch {
                    // update add team member state into loading
                    _addTeamMemberState = TeamDetailState.LoadingState

                    // add delay
                    delay(600)

                    // get user id and team id
                    val getUserId = event.userId
                    val getTeamId = event.teamId
                    val getRoleTeamId = event.teamRole

                    try{
                        // check if user already on team or not
                        val isUserOnTeam : Boolean = teamUseCase.checkUserOnTeamUseCase(
                            userId = getUserId,
                            teamId = getTeamId
                        )

                        Log.d("Check", "Check user id : ${getUserId}")

                        if(isUserOnTeam) {
                            // user already on team, update state
                            _addTeamMemberState = TeamDetailState.ErrorState(
                                errMsg = "Already On Team: User with id $getUserId already on team"
                            )

                            return@launch
                        }

                        // assign user to team
                        val teamUser = teamUseCase.assignUserTeamUseCase(
                            userId = getUserId,
                            teamId = getTeamId,
                            teamRoleId = getRoleTeamId
                        )

                        // update state
                        _addTeamMemberState = TeamDetailState.DataState(
                            data = teamUser
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                        _addTeamMemberState = TeamDetailState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is TeamDetailEvent.OnRemoveTeamMember -> {
                viewModelScope.launch {
                    // update state on remove team
                    _removeTeamMemberState = TeamDetailState.LoadingState

                    delay(600)

                    val getUserId = event.userId
                    val getTeamId = event.teamId

                    try{
                        val response = teamUseCase.unAssignUserTeamUseCase(
                            userId = getUserId,
                            teamId = getTeamId
                        )

                        _removeTeamMemberState = TeamDetailState.DataState(
                            data = response
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                        _removeTeamMemberState = TeamDetailState.ErrorState(
                            errMsg = errMsg
                        )
                    }
                }
            }

            is TeamDetailEvent.OnSearchConnection -> {
                viewModelScope.launch {
                    // update state of search connection
                    _searchConnectionState = TeamDetailState.LoadingState

                    delay(600)

                    val getName = event.name

                    // get current login user id
                    val getLoginUserId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""

                    try{
                        val getConnections = userConnectionUseCase.searchConnectionUseCase(
                            name = getName,
                            userId = getLoginUserId
                        )

                        // populated data for only search player
                        val getSearchConnection : MutableList<UserModel> = mutableListOf()

                        for (connectionDto in getConnections) {
                            val getUserId = if(getLoginUserId == connectionDto.connectionUserOwnerId)
                                connectionDto.connectionUserConnectionId
                                    else connectionDto.connectionUserOwnerId

                            // get user
                            val getUserConnection = authUseCase.getUserIdUseCase(
                                userId = getUserId
                            )

                            // add to list
                            getSearchConnection.add(getUserConnection)
                        }

                        // update state
                        _searchConnectionState = TeamDetailState.DataState(
                            data = getSearchConnection
                        )
                    } catch (e : Exception) {
                        val errMsg = "Error Happen : ${e.message}, ${e.stackTrace}"

                        _searchConnectionState = TeamDetailState.ErrorState(
                            errMsg = errMsg,
                        )
                    }
                }
            }
        }
    }

    fun updateCreateTeamRoleState(newState : TeamDetailState) {
        _createTeamRoleState = newState

        // get role team
        getAllRoleTeam()
    }

    fun updateDeleteTeamRoleState(newState : TeamDetailState) {
        _deleteTeamRoleState = newState

        // update role team
        getAllRoleTeam()

        // update header
        getTeamHeaderData()
    }

    fun updateAddTeamMemberState(newState : TeamDetailState) {
        _addTeamMemberState = newState

        // update get new team
        getTeamHeaderData()
    }

    fun updateRemoveTeamMemberState(newState: TeamDetailState) {
        _removeTeamMemberState = newState

        getTeamHeaderData()
    }

    private fun getAllRoleTeam(){
        viewModelScope.launch {
            // update role team into loading
            _roleOnTeamState = TeamDetailState.LoadingState

            delay(600)
            try{
                // get role model
                val getTeamRole = teamRoleUseCase.getAllTeamRoleUseCase(
                    teamId = (savedStateHandle.get<String>("teamId") ?: "0").toInt()
                )

                // update team role state
                _roleOnTeamState = TeamDetailState.DataState(
                    data = getTeamRole
                )
            } catch (e : Exception) {
                val errMsg = "Error Happen : ${e.message}"

                _roleOnTeamState = TeamDetailState.ErrorState(
                    errMsg = errMsg
                )
            }
        }
    }

    private fun getTeamHeaderData(){
        viewModelScope.launch {
            // update stat into loading
            _teamDetailState = TeamDetailState.LoadingState

            try{
                // get team detail
                val data = teamUseCase.getTeamUseCase(
                    teamId = (savedStateHandle.get<String>("teamId") ?: "0").toInt()
                )

                // update value for is team leader
                // get login user id
                val getLoginUserId = sharedPreferences.getString(Constants.USER_ID, "") ?: ""

                isTeamLeader = getLoginUserId == data.teamLeaderId

                // get team leader data
                val getTeamLeader : UserModel = authUseCase.getUserIdUseCase(
                    userId = data.teamLeaderId ?: ""
                )

                // update team leader on data
                data.teamLeader = getTeamLeader

                _teamDetailState = TeamDetailState.DataState(
                    data = data,
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