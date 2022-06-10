package com.example.myapplication.data

import com.example.myapplication.data.local.RestaurantsDao
import com.example.myapplication.data.model.LocalRestaurant
import com.example.myapplication.data.model.PartialLocalRestaurant
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.data.services.RestaurantsApiService
import com.example.myapplication.domain.repository.RestaurantRepository
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantsApiService: RestaurantsApiService,
    private val restaurantsDao: RestaurantsDao
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> {
        return restaurantsDao.getAll().map {
            Restaurant(
                id = it.id,
                title = it.title,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }
    }

    override suspend fun toggleFavoriteRestaurant(id: Int, isFavorite: Boolean) {
        restaurantsDao.update(PartialLocalRestaurant(id, isFavorite))
    }

    override suspend fun loadRestaurants() {
        try {
            refreshCache()
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException,
                is ConnectException,
                is HttpException -> {
                    if (restaurantsDao.getAll().isEmpty())
                        throw Exception(
                            "Something went wrong. " +
                                    "We have no data."
                        )
                }
                else -> throw e
            }
        }
    }

    override suspend fun getRestaurant(id: Int): Restaurant =
        restaurantsApiService.getRestaurant(id).values.first().let {
            Restaurant(
                id = it.id,
                title = it.title,
                description = it.description
            )
        }

    private suspend fun refreshCache() {

        val remoteRestaurants = restaurantsApiService.getRestaurants()
        val favoriteRestaurants = restaurantsDao.getAllFavorited()

        restaurantsDao.addAll(remoteRestaurants.map {
            LocalRestaurant(
                id = it.id,
                title = it.title,
                description = it.description
            )
        })
        restaurantsDao.updateAll(favoriteRestaurants.map { PartialLocalRestaurant(it.id, true) })
    }
}