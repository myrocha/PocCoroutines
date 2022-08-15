package com.example.poccoroutines.data

open class UseCaseError(val message: String = "")

/*
class NoConnectionError(val message: String = "")

open class ApiError(
    message: String,
    val code: Int
) : UseCaseError(message)

class UnauthorizedError(
    message: String,
    code: Int
) : ApiError(message, code)
*/
