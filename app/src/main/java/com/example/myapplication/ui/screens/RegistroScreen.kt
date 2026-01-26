package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.UsuarioViewModel
import com.example.myapplication.utils.ValidationUtils

@Composable
fun RegistroScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var run by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf<String?>(null) }
    var runError by remember { mutableStateOf<String?>(null) }
    var correoError by remember { mutableStateOf<String?>(null) }
    var numeroError by remember { mutableStateOf<String?>(null) }

    val isFormValid by derivedStateOf {
        nombre.isNotBlank() && run.isNotBlank() && correo.isNotBlank() && numero.isNotBlank() && password.isNotBlank() &&
                nombreError == null && runError == null && correoError == null && numeroError == null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                Text("Crear Cuenta", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(24.dp))

                // Nombre
                OutlinedTextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        nombreError = if (!ValidationUtils.isValidName(it)) "Nombre inválido" else null
                    },
                    label = { Text("Nombre Completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = nombreError != null
                )
                nombreError?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // RUN
                OutlinedTextField(
                    value = run,
                    onValueChange = {
                        run = it
                        runError = if (!ValidationUtils.isValidChileanRut(it)) "RUT inválido" else null
                    },
                    label = { Text("RUT (Ej: 12345678-9)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = runError != null
                )
                runError?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Correo
                OutlinedTextField(
                    value = correo,
                    onValueChange = {
                        correo = it
                        correoError = if (!ValidationUtils.isValidEmail(it)) "Correo inválido" else null
                    },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = correoError != null
                )
                correoError?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Número
                OutlinedTextField(
                    value = numero,
                    onValueChange = {
                        numero = it
                        numeroError = if (!ValidationUtils.isValidPhoneNumber(it)) "Número inválido" else null
                    },
                    label = { Text("Número de Teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = numeroError != null
                )
                numeroError?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        usuarioViewModel.insertarUsuario(correo, password, run, nombre, numero, false)
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    enabled = isFormValid
                ) {
                    Text("Registrarse", style = MaterialTheme.typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("¿Ya tienes una cuenta? Inicia sesión")
                }
            }
        }
    }
}
