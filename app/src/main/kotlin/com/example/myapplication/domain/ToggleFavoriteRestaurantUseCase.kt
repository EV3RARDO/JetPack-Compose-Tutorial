package com.example.myapplication.domain

import com.example.myapplication.domain.repository.RestaurantRepository
import javax.inject.Inject

/**
 * Business Rule:
 *
 * Whenever a user presses on the heart icon of a restaurant, we must toggle it's favorite
 * status to the opposite value
 */

class ToggleFavoriteRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
): CoroutineUseCase<ToggleFavoriteRestaurantUseCase.Params, Unit>() {

    override suspend fun execute(params: ToggleFavoriteRestaurantUseCase.Params) {
        restaurantRepository.toggleFavoriteRestaurant(
            id = params.id,
            isFavorite = !params.isFavorite
        )
    }

    data class Params(val id: Int, val isFavorite: Boolean)
}