package com.example.myapplication.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigationdemo.screens.Home
import com.example.myapplication.navigationdemo.screens.Profile
import com.example.myapplication.navigationdemo.screens.Welcome


class NavigationActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }


    @Composable
    fun MainScreen() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavRoutes.Home.route) {

            composable(NavRoutes.Home.route) {
                Home(navController = navController)
            }

            composable(NavRoutes.Welcome.route + "/{userName}") { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName")
                Welcome(navController = navController, userName = userName)
            }

            composable(NavRoutes.Profile.route) {
                Profile()
            }

        }

    }

    @Preview(fontScale = 1.2f)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }


}