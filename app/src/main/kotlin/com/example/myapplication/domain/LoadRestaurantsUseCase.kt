package com.example.myapplication.domain

import com.example.myapplication.IODispatcher
import com.example.myapplication.domain.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoadRestaurantsUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Unit>(ioDispatcher) {

    override suspend fun execute(params: Unit) {
        return restaurantRepository.loadRestaurants()
    }
}