package com.example.myapplication.ui.restaurantsDemo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainDispatcher
import com.example.myapplication.data.common.Result
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.LoadRestaurantsUseCase
import com.example.myapplication.domain.GetSortedRestaurantsUseCase
import com.example.myapplication.domain.ToggleFavoriteRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val loadRestaurantsUseCase: LoadRestaurantsUseCase,
    private val getSortedRestaurantsUseCase: GetSortedRestaurantsUseCase,
    private val toggleFavoriteRestaurantUseCase: ToggleFavoriteRestaurantUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = mutableStateOf(RestaurantScreenState())
    val state: State<RestaurantScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            isLoading = false,
            error = exception.message
        )
    }

    init {
        loadRestaurants()
    }

    fun toggleFavorite(id: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler + dispatcher) {

            val params = ToggleFavoriteRestaurantUseCase.Params(id = id, isFavorite = oldValue)
            toggleFavoriteRestaurantUseCase(params)

            when (val result = getSortedRestaurantsUseCase(Unit)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(restaurants = result.data)
                }
                is Result.Error -> { throw result.error}
            }
        }
    }

    private fun loadRestaurants() {
        viewModelScope.launch(errorHandler + dispatcher) {

            loadRestaurantsUseCase(Unit)

            when (val result = getSortedRestaurantsUseCase(Unit)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(restaurants = result.data, isLoading = false)
                }
                is Result.Error -> {
                    throw result.error
                }
            }
        }
    }
}

data class RestaurantScreenState(
    val restaurants: List<Restaurant> = listOf(),
    val isLoading: Boolean = true,
    val error: String? = null
)

