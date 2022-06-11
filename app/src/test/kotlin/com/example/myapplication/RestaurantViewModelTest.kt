package com.example.myapplication

import com.example.myapplication.data.RestaurantRepositoryImpl
import com.example.myapplication.domain.GetSortedRestaurantsUseCase
import com.example.myapplication.domain.LoadRestaurantsUseCase
import com.example.myapplication.domain.ToggleFavoriteRestaurantUseCase
import com.example.myapplication.ui.restaurantsDemo.RestaurantScreenState
import com.example.myapplication.ui.restaurantsDemo.RestaurantsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    private fun getViewModel(): RestaurantsViewModel {

        val restaurantRepository = RestaurantRepositoryImpl(
            restaurantsApiService = FakeApiService(),
            restaurantsDao = FakeRoomDao()
        )
        val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(restaurantRepository)
        val loadRestaurantsUseCase = LoadRestaurantsUseCase(restaurantRepository)
        val toggleFavoriteRestaurantUseCase = ToggleFavoriteRestaurantUseCase(restaurantRepository)


        return RestaurantsViewModel(
            getSortedRestaurantsUseCase = getSortedRestaurantsUseCase,
            loadRestaurantsUseCase = loadRestaurantsUseCase,
            toggleFavoriteRestaurantUseCase = toggleFavoriteRestaurantUseCase,
            dispatcher = dispatcher
        )
    }

    @Test
    fun initialState_isProduced() = scope.runTest {
        val viewModel = getViewModel()
        val initialState = viewModel.state.value

        assert(initialState == RestaurantScreenState(
            restaurants = emptyList(),
            isLoading = true,
            error = null
        ))
    }
}