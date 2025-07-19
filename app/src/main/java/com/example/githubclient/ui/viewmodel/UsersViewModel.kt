package com.example.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.data.model.User
import com.example.githubclient.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UsersUiState(val query: String = "", val users: List<User> = emptyList())

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: GithubRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }

        viewModelScope.launch {
            if (query.isNotBlank()) {
                val users = repository.searchUsers(query)
                _uiState.update { it.copy(users = users) }
            } else {
                _uiState.update { it.copy(users = emptyList()) }
            }
        }
    }
}
