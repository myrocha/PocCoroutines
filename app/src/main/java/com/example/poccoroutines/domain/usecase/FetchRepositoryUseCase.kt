package com.example.poccoroutines.domain.usecase

import com.example.poccoroutines.data.Result
import com.example.poccoroutines.data.model.RepositoryResponse
import com.example.poccoroutines.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow

class FetchRepositoryUseCase(private val repository: GitHubRepository) {

    suspend fun fetchRepositoriesCoroutines() : Result<List<RepositoryResponse>>{
        return repository.getRepositoriesCoroutine()

    }

    fun fetchRepositoriesFlow() : Flow<List<RepositoryResponse>> {
        return repository.getRepositoriesFlow()
    }
}