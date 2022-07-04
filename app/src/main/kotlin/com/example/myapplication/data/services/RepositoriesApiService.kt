package com.example.myapplication.data.services

import com.example.myapplication.data.model.RepositoriesResponse

import retrofit2.http.GET
import retrofit2.http.Query


interface RepositoriesApiService {

    @GET("repositories?q=mobile&sort=stars&per_page=20")
    suspend fun getRepositories(@Query("page") page: Int): RepositoriesResponse
}