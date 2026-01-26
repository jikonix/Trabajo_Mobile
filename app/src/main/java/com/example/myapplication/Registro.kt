package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
class Registro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val context = LocalContext.current
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Ventana 3") },

                            )
                        //BackButton
                        IconButton(onClick = {
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }) {
                            Icon(Icons.Filled.ArrowBack, null)

                        }
                    }
                ) { innerPadding ->

                    RegisterScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RegisterScreen(viewModel: UsuarioViewModel = viewModel(), modifier: Modifier = Modifier) {
    var correo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var run by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var log by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val usuarios = viewModel.usuarios.collectAsState(initial = emptyList())
    //add topappbar
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ventana de Registro", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text("correo", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = correo, onValueChange = { correo = it })
        Text("Nombre", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = nombre, onValueChange = { nombre = it })
        Text("run", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = run, onValueChange = { run = it })
        Text("Numero telefonico +56 9 ", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = numero, onValueChange = { numero = it })
        Text("Contraseña", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
        )
        Text("Confirme contraseña", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = password2, onValueChange = { password2 = it })
        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        LazyColumn() {
            items(usuarios.value.size) {
                Text(text = "Correo del Usuario: " + usuarios.value[it].correo)

            }
        }
        //Rectangle shape button with rounded corner
        OutlinedButton(
            onClick = {
                viewModel.insertarUsuario(correo, password, run, nombre, numero.toIntOrNull() ?: 0, rol)
                Toast.makeText(
                    context,
                    "Agregado con exito",
                    Toast.LENGTH_LONG,
                ).show()
                correo = ""
                nombre = ""
                run = ""
                numero = ""
                rol= false
                password = ""
                password2 = ""


            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Registrar")


        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MyApplicationTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Ventana 3") },
                    navigationIcon = {
                        IconButton(onClick = { /*Volver*/ }) {
                            Icon(Icons.Filled.ArrowBack, null)

                        }
                    },
                    actions = {
                        IconButton(onClick = { /* acción */ }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->

            RegisterScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

        }
    }
}
