package com.example.myapplication.ui.restaurantsDemo.restaurantdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.common.Result
import com.example.myapplication.domain.GetRestaurantByIdUseCase
import com.example.myapplication.domain.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UiState())
    val state: State<UiState>
        get()  = _state


    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(error = exception.message)
    }

    init {
        val id = stateHandle.get<Int>("restaurant_id") ?: 0
        getRestaurantById(id)
    }

    private fun getRestaurantById(id: Int) {
        viewModelScope.launch(errorHandler) {
            val params = GetRestaurantByIdUseCase.Params(id = id)
            when (val result = getRestaurantByIdUseCase(params)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(restaurant = result.data)
                }

                is Result.Error -> {
                    Timber.d("${result.error}")
                    throw result.error
                }
            }
        }
    }

    data class UiState(
        val restaurant: Restaurant? = null,
        val error: String? = null
    )
}

