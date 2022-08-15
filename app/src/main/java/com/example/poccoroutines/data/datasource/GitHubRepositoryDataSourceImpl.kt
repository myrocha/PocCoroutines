package com.example.poccoroutines.data.datasource

import com.example.poccoroutines.data.ErrorMapper
import com.example.poccoroutines.data.Result
import com.example.poccoroutines.data.api.GitHubApi
import com.example.poccoroutines.data.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

private const val NAME_REPOSITORY = "kotlin"


class GitHubRepositoryDataSourceImpl(
    private val api: GitHubApi, private val errorMapper: ErrorMapper = ErrorMapper()
) : GitHubRepositoryDataSource {

    override suspend fun fetchRepositoriesCoroutines(): Result<List<RepositoryResponse>> {
        return try {
            Result.Success(api.getRepositories(NAME_REPOSITORY))
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    override fun fetchRepositoriesFlow(): Flow<List<RepositoryResponse>> {
        return flow {
            emit(api.getRepositories(NAME_REPOSITORY))
        }.catch {
            errorMapper.map(it)
        }
    }

    private fun <T> handleError(exception: Exception) =
        Result.Error<T>(errorMapper.map(exception))


}