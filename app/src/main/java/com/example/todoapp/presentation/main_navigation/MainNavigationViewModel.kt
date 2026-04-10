package com.example.todoapp.presentation.main_navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.usecase.local_user_manager_usecase.LocalUserManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainNavigationViewModel @Inject constructor(
    private val localUserManagerUseCase: LocalUserManagerUseCase
) : ViewModel() {
    fun onLogout() {
        viewModelScope.launch {
            localUserManagerUseCase.setUserLogOut()
        }
    }
}