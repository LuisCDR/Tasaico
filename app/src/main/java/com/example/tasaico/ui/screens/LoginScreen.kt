package com.example.tasaico.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tasaico.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.minutes

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var valueRUC by remember { mutableStateOf("") }
    var valueUser by remember { mutableStateOf("") }
    var valuePassword by remember { mutableStateOf("") }
    var activated by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                state = rememberScrollState(),
                reverseScrolling = true
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tasaico_logo),
            modifier = Modifier
                .size(300.dp)
                .padding(10.dp, 0.dp, 10.dp, 50.dp)
                .clip(RoundedCornerShape(40.dp)),
            contentDescription = null,
            alignment = Alignment.TopCenter
        )
        OutlinedTextField(
            value = valueRUC,
            onValueChange = { valueRUC = it },
            label = { Text(text = "R.U.C.") },
            shape = RoundedCornerShape(10.dp)
        )
        OutlinedTextField(
            value = valueUser,
            onValueChange = { valueUser = it },
            label = { Text(text = "Usuario") },
            shape = RoundedCornerShape(10.dp)
        )
        OutlinedTextField(
            value = valuePassword,
            onValueChange = { valuePassword = it },
            label = { Text(text = "Contraseña") },
            shape = RoundedCornerShape(10.dp),
            visualTransformation = PasswordVisualTransformation('*')
        )
        CheckBoxLabel(
            modifier = Modifier
                .padding(50.dp, 10.dp)
                .fillMaxWidth(),
            checked = false,
            onCheckedChange = {}
        ){
            Text(
                text = "Recordar Usuario",
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }
        Button(
            onClick = {
            if (
                valueRUC.isNotEmpty() &&
                valueUser.isNotEmpty() &&
                valuePassword.isNotEmpty()
            ) {
                activated = true
                coroutineScope.launch(Dispatchers.IO) {
                    delay(0.01.minutes)
                    coroutineScope.launch(Dispatchers.Main) {
                        onLogin()
                    }
                }
            }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 10.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Iniciar Sesión")
            if(activated) {
                LoadingDialog(
                    modifier = Modifier.size(300.dp, 200.dp),
                    onDismissRequest = { onLogin() }
                ) {
                    Text("Iniciando sesion,")
                    Text("espere un momento, por favor")
                }
            }
        }
    }
}

@Composable
fun CheckBoxLabel(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    text: (@Composable () -> Unit)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
        text()
    }
}

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {},
        modifier = modifier,
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    //LoginScreen()
}