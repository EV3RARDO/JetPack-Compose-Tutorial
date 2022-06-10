package com.example.myapplication.ui.restaurantsDemo

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.restaurantsDemo.restaurantdetails.RestaurantDetailsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class RestaurantsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                RestaurantsApp()
            }
        }
    }
}

@Composable
private fun RestaurantsApp() {
    /**
     * The NavController object handles the navigation between composable screens -
     * it operates on the back stack of composable destinations. This means that across
     * recompositions, it must keep the current state of the navigation stack. For that
     * to happen, it must be a stateful object.
     */
    val navController = rememberNavController()


    /**
     * NavHost displays the composable destinations. Every time a navigation actions
     * between composable is done, the content within NavHost is recomposed automatically.
     *
     * Parameters:
     *  1. A navController: NavHOstController object that is associated with a single NavHost
     *  composable. NavHost links NavController with a navigation graph that defines the possible
     *  destinations of the application.
     *
     *  2. A startDestination: String object that defines the entry-point route of the navigation graph.
     *
     *  3. The builder: NavGraphBuilder.() -> Unit trailing lambda parameter, which uses the lambda
     *  syntax from the Navigation Kotlin DSL to construct a navigation graph.
     */
    NavHost(navController = navController, startDestination = "restaurants") {
        composable(route = "restaurants") {

            val viewModel: RestaurantsViewModel = hiltViewModel()

            RestaurantsScreen(
                state = viewModel.state.value,
                onItemClick = { id ->
                    navController.navigate("restaurants/$id")
                },
                onFavoriteClick = { id: Int, oldValue: Boolean ->
                    viewModel.toggleFavorite(id = id, oldValue = oldValue)
                }
            )
        }
        composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id") {
                type = NavType.IntType
            }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "www.restaurantsapp.details.com/{restaurant_id}"
            })
        ) {
            RestaurantDetailsScreen()
        }
    }
}