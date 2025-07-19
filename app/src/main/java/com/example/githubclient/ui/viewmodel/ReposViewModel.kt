package com.example.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.data.model.Repo
import com.example.githubclient.data.model.User
import com.example.githubclient.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReposUiState(val user: User? = null, val repos: List<Repo> = emptyList())

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repository: GithubRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ReposUiState())
    val uiState: StateFlow<ReposUiState> = _uiState.asStateFlow()

    fun loadUser(username: String) {
        viewModelScope.launch {
            val user = repository.getUser(username)
            val repos = repository.getRepos(username).filter { !it.fork }
            _uiState.update { it.copy(user = user, repos = repos) }
        }
    }
}
