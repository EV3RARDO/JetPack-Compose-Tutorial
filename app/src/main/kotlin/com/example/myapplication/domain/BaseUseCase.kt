package com.example.myapplication.domain

abstract class BaseUseCase<in T, out R> {

    suspend operator fun invoke(params: T): R {
        return execute(params)
    }

    protected abstract suspend fun execute(params: T): R
}

suspend operator fun <R> BaseUseCase<Unit, R>.invoke(): R = this(Unit)