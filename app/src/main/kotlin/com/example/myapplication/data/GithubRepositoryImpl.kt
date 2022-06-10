package com.example.myapplication.data

import com.example.myapplication.data.services.GithubService
import com.example.myapplication.data.model.User
import com.example.myapplication.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRepository {

    override suspend fun getUsers(): List<User> = githubService.getUsers()
}