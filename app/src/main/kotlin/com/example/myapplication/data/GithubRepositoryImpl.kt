package com.example.myapplication.data

import com.example.myapplication.data.services.RepositoriesApiService
import com.example.myapplication.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val repositoriesApiService: RepositoriesApiService
) : GithubRepository {

}