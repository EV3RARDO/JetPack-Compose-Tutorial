package com.example.myapplication.domain

import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantByIdUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
): CoroutineUseCase<GetRestaurantByIdUseCase.Params, Restaurant>() {

    data class Params(val id: Int)

    override suspend fun execute(params: Params): Restaurant {
        return restaurantRepository.getRestaurant(params.id)
    }
}