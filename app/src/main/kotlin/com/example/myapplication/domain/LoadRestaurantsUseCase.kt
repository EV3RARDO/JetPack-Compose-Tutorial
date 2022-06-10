package com.example.myapplication.domain

import com.example.myapplication.domain.repository.RestaurantRepository
import javax.inject.Inject

class LoadRestaurantsUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : CoroutineUseCase<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        return restaurantRepository.loadRestaurants()
    }
}