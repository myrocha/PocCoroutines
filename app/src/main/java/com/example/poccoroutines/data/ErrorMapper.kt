package com.example.poccoroutines.data

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorMapper {

    fun map(throwable: Throwable): UseCaseError {
        when (throwable) {
            is ConnectException,
            is SocketTimeoutException,
            is UnknownHostException -> {
                return UseCaseError(noConnectionMessage())
            }
            is HttpException -> {
                parseMessage(throwable)
            }
        }
        return UseCaseError(defaultMessage())
    }

    private fun parseMessage(exception: HttpException): UseCaseError {
        exception.message().let { error ->
            return try {
                Gson().fromJson(
                    error,
                    UseCaseError::class.java
                )
            } catch (e: Exception) {
                return UseCaseError(defaultMessage())
            }
        }
    }

    private fun defaultMessage() = "Ops, parece que algo deu errado"

    private fun noConnectionMessage() = "Ops, verifique sua conexão com a internet"
}