package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.User
import retrofit2.http.GET

interface GithubRepository {

    @GET
    suspend fun getUsers(): List<User>
}