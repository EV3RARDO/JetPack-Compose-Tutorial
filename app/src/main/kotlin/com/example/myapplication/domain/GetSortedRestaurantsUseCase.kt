package com.example.myapplication.domain

import com.example.myapplication.IODispatcher
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Business Rule: Sort Restaurants Alphabetically.
 */

class GetSortedRestaurantsUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<Restaurant>>(ioDispatcher) {

    override suspend fun execute(params: Unit): List<Restaurant> {
        return restaurantRepository.getRestaurants().sortedBy { it.title }
    }
}