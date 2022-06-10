package com.example.myapplication.data.services

import com.example.myapplication.data.model.User
import retrofit2.http.GET

interface GithubService {

    @GET("/users")
    suspend fun getUsers(): List<User>

}