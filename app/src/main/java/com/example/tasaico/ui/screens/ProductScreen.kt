package com.example.tasaico.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tasaico.ui.utils.AppScaffold

@Composable
fun ProductScreen(navController: NavController, name: String?) {
    AppScaffold(title = "Productos para $name") {
        IconButton(onClick = {
            navController.popBackStack(
                route = "home",
                inclusive = false
            )
        }) {
            IconWith(imageVector = Icons.Default.ArrowBack)
        }
        ColumnItems(data = products) {
            Product(name = it, navController = navController)
        }
    }
}

val products = listOf(
    "Pan",
    "Queque",
    "Paneton",
)

@Composable
fun Product(name: String, navController: NavController) {
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(5.dp, 10.dp, 5.dp, 5.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )
            .background(color = Color.White)
            .clickable { navController.navigate("products/$name/details") },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = { checked = it })
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
        Text(text = name)
    }
}