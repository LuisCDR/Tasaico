package com.example.tasaico.ui.screens

import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.tasaico.ui.utils.AppScaffold

@Composable
fun Products(navController: NavController, name: String?) {
    AppScaffold(title = "Productos para $name") {
        IconButton(onClick = {
            navController.popBackStack(
                route = "home",
                inclusive = true
            )
        }) {
            IconWith(Icons.Default.ArrowBack)
        }
    }
}