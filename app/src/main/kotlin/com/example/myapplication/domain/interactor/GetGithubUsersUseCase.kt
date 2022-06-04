package com.example.myapplication.domain.interactor

import com.example.myapplication.domain.BaseUseCase
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.GithubRepository
import javax.inject.Inject

class GetGithubUsersUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseUseCase<Unit, List<User>>() {

    override suspend fun execute(params: Unit): List<User> {
        return githubRepository.getUsers()
    }
}