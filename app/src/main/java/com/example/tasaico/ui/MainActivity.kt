package com.example.tasaico.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasaico.ui.screens.DetailScreen
import com.example.tasaico.ui.screens.HomeScreen
import com.example.tasaico.ui.screens.LoginScreen
import com.example.tasaico.ui.screens.ProductScreen
import com.example.tasaico.ui.theme.TasaicoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasaicoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    //LoginScreen()
                    AppNavigation(startDestination = "login")
                }
            }
        }
    }
}

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "login") {
            LoginScreen {
                navController.navigate("home")
            }
        }
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(
            route = "detail/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController, backStackEntry.arguments?.getString("name"))
        }
        composable(
            route = "products/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            ProductScreen(navController, backStackEntry.arguments?.getString("name"))
        }
        composable(
            route = "products/{name}/details",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController, backStackEntry.arguments?.getString("name"))
        }
    }
}