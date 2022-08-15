package com.example.poccoroutines.presentation.viewmodel.state

import com.example.poccoroutines.data.model.RepositoryResponse

data class GitHubRepositoryState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isShowList: Boolean = false,
    val list: List<RepositoryResponse>? = null,
    val isEmpty: Boolean = false
) {
    fun setLoading() = copy(isLoading = true, isError = false, isShowList = false, list = null)
    fun setListRepository(listRepository: List<RepositoryResponse>) = copy(isLoading = false, isError = false, isShowList = true, list = listRepository)
    fun setError() = copy(isLoading = false, isError = true, isShowList = false, list = null)
}