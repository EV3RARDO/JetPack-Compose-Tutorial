package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.User

interface GithubRepository {

    suspend fun getUsers(): List<User>
}