package com.example.poccoroutines.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccoroutines.data.Result
import com.example.poccoroutines.domain.usecase.FetchRepositoryUseCase
import com.example.poccoroutines.presentation.viewmodel.action.GitHubRepositoryAction
import com.example.poccoroutines.presentation.viewmodel.state.GitHubRepositoryState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GitHubRepositoryViewModel(private var useCase: FetchRepositoryUseCase, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val states = MutableStateFlow(GitHubRepositoryState())
    private val actions = MutableSharedFlow<GitHubRepositoryAction>()

    fun getListRepositoryCoroutines() {
        viewModelScope.launch {
            states.emit(GitHubRepositoryState().setLoading())
            when (val result = useCase.fetchRepositoriesCoroutines()) {
                is Result.Success -> {
                    states.emit(GitHubRepositoryState().setListRepository(result.data))
                }
                is Result.Error -> {
                    states.emit(GitHubRepositoryState().setError())
                }
            }
        }
    }


    fun getListRepositoryFlow() {
        viewModelScope.launch {
            useCase.fetchRepositoriesFlow()
                .flowOn(dispatcher)
                .onStart { states.update { it.setLoading() } }
                .catch { states.update { it.setError() } }
                .collect { listRepository -> states.update { it.setListRepository(listRepository) } }
        }
    }

    fun getState() = states.asStateFlow()
}