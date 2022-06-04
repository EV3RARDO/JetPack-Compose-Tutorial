package com.example.myapplication.data.common

import retrofit2.HttpException
import retrofit2.Response

/**
 * Used to handle the results of a request called in one of the services.
 */
sealed class Result<T> {

    fun isSuccessful() = this is Success

    /**
     * Represents a network result that successfully received a response containing body data.
     */
    class Success<T>(val data: T) : Result<T>()


    /**
     * Represents a network result that successfully received a response containing an error message
     */
    class Error<T>(val code: Int, val message: String?) : Result<T>()

    /**
     * Represents a network result that faced an unexpected exception before getting a response from the network
     * such as IOException and UnknownHostException
     */
    class Exception<T>(val e: Throwable) : Result<T>()

}


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
}