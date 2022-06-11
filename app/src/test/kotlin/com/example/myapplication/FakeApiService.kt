package com.example.myapplication

import com.example.myapplication.data.model.RemoteRestaurant
import com.example.myapplication.data.services.RestaurantsApiService
import com.example.myapplication.ui.restaurantsDemo.DummyContent
import kotlinx.coroutines.delay

class FakeApiService: RestaurantsApiService {

    override suspend fun getRestaurants(): List<RemoteRestaurant> {
        delay(100)
        return DummyContent.getRemoteRestaurants()
    }

    override suspend fun getRestaurant(id: Int): Map<String, RemoteRestaurant> {
        TODO()
    }
}