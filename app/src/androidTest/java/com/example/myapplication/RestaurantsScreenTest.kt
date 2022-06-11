package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.myapplication.ui.restaurantsDemo.Description
import com.example.myapplication.ui.restaurantsDemo.DummyContent
import com.example.myapplication.ui.restaurantsDemo.RestaurantScreenState
import com.example.myapplication.ui.restaurantsDemo.RestaurantsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.junit.Rule
import org.junit.Test

class RestaurantsScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun initialState_isRendered() {
        testRule.setContent {
            MyApplicationTheme {
                RestaurantsScreen(
                    state = RestaurantScreenState(restaurants = emptyList(), isLoading = true),
                    onFavoriteClick = { _: Int, _: Boolean -> },
                    onItemClick = {})

            }
        }

        testRule.onNodeWithContentDescription(Description.RESTAURANTS_LOADING).assertIsDisplayed()
    }

    @Test
    fun stateWithContent_isRendered() {
        val restaurants = DummyContent.getDomainRestaurants()
        testRule.setContent {
            MyApplicationTheme {
                RestaurantsScreen(
                    state = RestaurantScreenState(restaurants = restaurants, isLoading = false),
                    onFavoriteClick = { _: Int, _: Boolean -> },
                    onItemClick = {})
            }
        }

        testRule.onNodeWithText(restaurants[0].title).assertIsDisplayed()
        testRule.onNodeWithText(restaurants[0].description).assertIsDisplayed()
        testRule.onNodeWithContentDescription(Description.RESTAURANTS_LOADING).assertDoesNotExist()
    }


    @Test
    fun stateWithContent_ClickOnItem_isRegistered() {
        val restaurants = DummyContent.getDomainRestaurants()
        val targetRestaurant = restaurants[0]
        testRule.setContent {
            MyApplicationTheme {
                RestaurantsScreen(
                    state = RestaurantScreenState(restaurants = restaurants, isLoading = false),
                    onFavoriteClick = { _: Int, _: Boolean -> },
                    onItemClick = { id ->
                        assert(id == targetRestaurant.id)
                    })
            }
        }

        testRule.onNodeWithText(targetRestaurant.title).performClick()


    }
}