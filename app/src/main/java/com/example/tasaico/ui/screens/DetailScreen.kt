package com.example.tasaico.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tasaico.ui.utils.AppScaffold

@Composable
fun DetailScreen(navController: NavController, name: String?) {
    AppScaffold(title = "Detalles de $name") {
        IconButton(onClick = {
            navController.popBackStack(
                route = "home",
                inclusive = false
            )
        }) {
            IconWith(imageVector = Icons.Default.ArrowBack)
        }
        Space(height = 20)
        IconWith(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Default.Person
        )
        Space(height = 10)
        Text(text =  name!!.uppercase())
    }
}

@Composable
fun IconWith(
    modifier: Modifier = Modifier,
    imageVector: ImageVector
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
        )
    }
}

@Composable
fun Space(height: Int) {
    Spacer(modifier = Modifier
        .fillMaxSize()
        .height(height.dp))
}