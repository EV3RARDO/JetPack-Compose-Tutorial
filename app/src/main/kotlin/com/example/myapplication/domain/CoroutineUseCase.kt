package com.example.myapplication.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<in T, out R>(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(params: T): com.example.myapplication.data.common.Result<R> =
        withContext(dispatcher) {
            return@withContext try {
                val data = execute(params)
                com.example.myapplication.data.common.Result.Success(data)
            } catch (ex: Exception) {
                com.example.myapplication.data.common.Result.Error(ex)
            }
        }

    abstract suspend fun execute(params: T): R
}
