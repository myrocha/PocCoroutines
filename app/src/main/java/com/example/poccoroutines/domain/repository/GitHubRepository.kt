package com.example.poccoroutines.domain.repository

import com.example.poccoroutines.data.Result
import com.example.poccoroutines.data.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

   suspend fun getRepositoriesCoroutine() : Result<List<RepositoryResponse>>
   fun getRepositoriesFlow() : Flow<List<RepositoryResponse>>
}