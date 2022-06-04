package com.example.myapplication.domain.repository

import com.example.myapplication.data.services.GithubService
import com.example.myapplication.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GithubRepository {

    override suspend fun getUsers(): List<User> =
        withContext(ioDispatcher) { githubService.getUsers() }
}