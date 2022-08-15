package com.example.poccoroutines.data

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    class Error<T>(val error: UseCaseError) : Result<T>()
}
