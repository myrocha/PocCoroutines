package com.example.poccoroutines.data

import com.example.poccoroutines.data.datasource.GitHubRepositoryDataSource
import com.example.poccoroutines.domain.repository.GitHubRepository
import com.example.poccoroutines.data.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow


class GitHubRepositoryImpl(
    private val dataSource: GitHubRepositoryDataSource,
    private val errorMapper: ErrorMapper = ErrorMapper()
) : GitHubRepository {

    override suspend fun getRepositoriesCoroutine(): Result<List<RepositoryResponse>> {
        return dataSource.fetchRepositoriesCoroutines()
    }

    private fun <T> handleError(exception: Exception) = Result.Error<T>(errorMapper.map(exception))


    override fun getRepositoriesFlow(): Flow<List<RepositoryResponse>> {
        return dataSource.fetchRepositoriesFlow()
    }
}
