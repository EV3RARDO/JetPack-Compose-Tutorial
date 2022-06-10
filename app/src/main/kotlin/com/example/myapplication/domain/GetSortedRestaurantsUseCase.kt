package com.example.myapplication.domain

import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.repository.RestaurantRepository
import javax.inject.Inject

/**
 * Business Rule: Sort Restaurants Alphabetically.
 */

class GetSortedRestaurantsUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : CoroutineUseCase<Unit, List<Restaurant>>() {

    override suspend fun execute(params: Unit): List<Restaurant> {
        return restaurantRepository.getRestaurants().sortedBy { it.title }
    }
}