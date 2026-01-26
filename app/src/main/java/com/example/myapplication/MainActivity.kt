@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.myapplication.R.drawable.login_icon as loginImageVector


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("logueo de credenciales") },
                            navigationIcon = {
                                Icon(
                                    painter = painterResource(id = loginImageVector),
                                    contentDescription = "Logo",

                                    )
                            }
                        )
                    }
                ) { innerPadding ->
                    Inicial(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicial(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Row() {
                Column() {
                    //Centered content
                    Text(
                        "Hola",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(onClick = {
                        context.startActivity(Intent(context, Registro::class.java))
                    }) {
                        Text("registrarse")
                    }
                }
            }
        }
    }
}

@Composable
fun Formulario(modifier: Modifier = Modifier) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    var context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = loginImageVector),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Text(
            "Correo",
            modifier = modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(value = correo, onValueChange = { correo = it })
        Text(
            "Password",
            modifier = modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            //Type password
            visualTransformation = PasswordVisualTransformation(),
        )
        Text(
            "Confirme contraseña",
            modifier = modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = pass2,
            onValueChange = { pass2 = it },
            //Type password
            visualTransformation = PasswordVisualTransformation(),
        )
        //Circle progress loader
        Spacer(modifier = Modifier.width(16.dp))
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                strokeWidth = 4.dp,
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedButton(
            onClick = {
                isLoading = true
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000L)
                    isLoading = false
                }

            },
            enabled = !isLoading,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(if (isLoading) "Cargando..." else "Enviar")
        }


    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Formulario(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}