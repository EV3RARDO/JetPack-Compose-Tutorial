package com.example.myapplication.data.common

/**
 * Used to handle the results of a request called in one of the services.
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error<out T>(val error: Throwable) : Result<T>()
}


/*
suspend fun <T> handleApi(execute: suspend () -> Response<T>): Result<T> {
    return try {
        val response = execute()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            Result.Success(body)
        } else {
            Result.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Result.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Result.Exception(e)
    }
}*/
