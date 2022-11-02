package com.example.tasaico.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    //state: ScaffoldState,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = modifier,
        //scaffoldState = state,
        topBar = { TopBar(title) }
    ) {padding ->
        Column(modifier = Modifier.padding(padding)) {
            content()
        }
    }
}

@Composable
fun TopBar(title: String) {
    TopAppBar(
        elevation = 5.dp,
        contentPadding = PaddingValues(5.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }
        Text(text = title)
    }
}