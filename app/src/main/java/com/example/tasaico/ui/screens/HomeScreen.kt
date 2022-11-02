package com.example.tasaico.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tasaico.ui.utils.AppScaffold

@Composable
fun HomeScreen(navController: NavController) {
    var valueCustomer by remember { mutableStateOf("") }

    AppScaffold(title = "CLIENTES") {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = valueCustomer,
                onValueChange = { valueCustomer = it },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(60.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            )
            Button(
                onClick = { if (
                    valueCustomer.isNotEmpty() &&
                    customers.contains(valueCustomer)
                ) {
                    navController.navigate("products/$valueCustomer")
                }
                          },
                modifier = Modifier
                    .height(40.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Continuar")
            }
        }
        ColumnItems(data = customers) {
            Customer(name = it, navController)
        }
    }
}

val customers = listOf(
    "Ana",
    "José",
    "Miguel",
    "Lucía",
    "Andres",
    "Luciana",
    "Juan",
    "María"
)

@Composable
fun ColumnItems(
    data: List<String>,
    content: @Composable (String) -> Unit) {
    LazyColumn {
        items(items = data) { item ->
            content(item)
        }
    }
}

@Composable
fun Customer(name: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp, 10.dp, 5.dp, 5.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )
            .background(color = Color.White)
            .clickable { navController.navigate("products/$name") },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}