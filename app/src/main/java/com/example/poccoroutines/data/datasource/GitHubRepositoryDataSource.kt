package com.example.poccoroutines.data.datasource

import com.example.poccoroutines.data.Result
import com.example.poccoroutines.data.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface GitHubRepositoryDataSource {

    suspend fun fetchRepositoriesCoroutines() : Result<List<RepositoryResponse>>
    fun fetchRepositoriesFlow() : Flow<List<RepositoryResponse>>
}