package com.example.poccoroutines.data.api

import com.example.poccoroutines.data.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): List<RepositoryResponse>

}