package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Restaurant

interface RestaurantRepository {

    suspend fun toggleFavoriteRestaurant(id: Int, isFavorite: Boolean)

    suspend fun loadRestaurants()

    suspend fun getRestaurants(): List<Restaurant>

    suspend fun getRestaurant(id: Int): Restaurant
}