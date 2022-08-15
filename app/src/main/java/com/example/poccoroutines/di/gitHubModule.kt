package com.example.poccoroutines.di

import com.example.poccoroutines.data.api.GitHubApi
import com.example.poccoroutines.domain.repository.GitHubRepository
import com.example.poccoroutines.data.GitHubRepositoryImpl
import com.example.poccoroutines.data.datasource.GitHubRepositoryDataSource
import com.example.poccoroutines.data.datasource.GitHubRepositoryDataSourceImpl
import com.example.poccoroutines.domain.usecase.FetchRepositoryUseCase
import com.example.poccoroutines.presentation.viewmodel.GitHubRepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val gitHubModule = module {

    viewModel {
        GitHubRepositoryViewModel(get())
    }

    factory {
        FetchRepositoryUseCase(get())
    }

    factory<GitHubRepository> {
        GitHubRepositoryImpl(get())
    }

    factory<GitHubRepositoryDataSource> {
        GitHubRepositoryDataSourceImpl(get())
    }
    factory<GitHubApi> {
        get<Retrofit>().create(GitHubApi::class.java)
    }

}