package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificarPerfilScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val user by usuarioViewModel.loggedInUser.collectAsState()

    var nombre by remember { mutableStateOf(user?.nombre ?: "") }
    var numero by remember { mutableStateOf(user?.numero ?: "") }
    var password by remember { mutableStateOf("") } // La contraseña se deja en blanco por seguridad

    val isFormValid by derivedStateOf {
        nombre.isNotBlank() && numero.isNotBlank()
    }

    val hasChanges by derivedStateOf {
        nombre != user?.nombre || numero != user?.numero || password.isNotBlank()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modificar Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre Completo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = user?.run ?: "",
                        onValueChange = {}, // No se puede cambiar
                        label = { Text("RUN") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = user?.correo ?: "",
                        onValueChange = {}, // No se puede cambiar
                        label = { Text("Correo Electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = numero,
                        onValueChange = { numero = it },
                        label = { Text("Número de Teléfono") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Nueva Contraseña (opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val finalPassword = if (password.isNotBlank()) password else user!!.password
                            usuarioViewModel.updateUsuario(
                                correo = user!!.correo,
                                password = finalPassword,
                                run = user!!.run,
                                nombre = nombre,
                                numero = numero,
                                rol = user!!.rol
                            )
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        enabled = isFormValid && hasChanges
                    ) {
                        Text("Guardar Cambios", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}