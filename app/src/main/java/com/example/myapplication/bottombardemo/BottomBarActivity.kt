package com.example.myapplication.bottombardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.bottombardemo.screens.Contacts
import com.example.myapplication.bottombardemo.screens.Favorites
import com.example.myapplication.bottombardemo.screens.Home

class BottomBarActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }


    @Composable
    fun MainScreen() {

        val navController = rememberNavController()

        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Bottom Navigation Demo") }) },
            content = { NavigationHost(navController = navController) },
            bottomBar = { BottomNavigationBar(navController = navController) }
        )

    }

    @Composable
    fun NavigationHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = NavRoutes.Home.route) {

            composable(NavRoutes.Home.route) {
                Home()
            }

            composable(NavRoutes.Contacts.route) {
                Contacts()
            }


            composable(NavRoutes.Favorites.route) {
                Favorites()
            }

        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {

        BottomNavigation {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            NavBarItems.BarItems.forEach { navItem ->
                BottomNavigationItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = navItem.image,
                            contentDescription = navItem.title
                        )
                    },
                    label = {
                        Text(text = navItem.title)
                    })
            }
        }
    }
}