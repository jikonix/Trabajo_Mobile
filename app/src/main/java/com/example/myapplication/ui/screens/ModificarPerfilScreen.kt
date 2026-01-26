package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificarPerfilScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val user by usuarioViewModel.loggedInUser.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Sincroniza el estado local cuando el usuario del ViewModel cambia
    LaunchedEffect(user) {
        user?.let {
            nombre = it.nombre
            numero = it.numero
        }
    }

    var nombreError by remember { mutableStateOf<String?>(null) }
    var numeroError by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val isFormValid by remember { derivedStateOf {
        nombre.isNotBlank() && numero.isNotBlank() && nombreError == null && numeroError == null
    }}

    val hasChanges by remember { derivedStateOf {
        user?.let {
            nombre != it.nombre || numero != it.numero || password.isNotBlank()
        } ?: false
    }}

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
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            if (user == null) {
                CircularProgressIndicator()
            } else {
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
                            onValueChange = {
                                nombre = it
                                nombreError = if (it.isBlank()) "El nombre no puede estar vacío" else if (!ValidationUtils.isValidName(it)) "Nombre inválido (solo letras)" else null
                            },
                            label = { Text("Nombre Completo") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = nombreError != null
                        )
                        nombreError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = user!!.run,
                            onValueChange = {}, // No se puede cambiar
                            label = { Text("RUN") },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = user!!.correo,
                            onValueChange = {}, // No se puede cambiar
                            label = { Text("Correo Electrónico") },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = numero,
                            onValueChange = {
                                numero = it
                                numeroError = if (it.isBlank()) "El número no puede estar vacío" else if (!ValidationUtils.isValidPhoneNumber(it)) "Número inválido (solo números)" else null
                            },
                            label = { Text("Número de Teléfono") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = numeroError != null,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )
                        numeroError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }
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
                                coroutineScope.launch {
                                    try {
                                        user?.let { userValue ->
                                            val finalPassword = if (password.isNotBlank()) password else userValue.password
                                            usuarioViewModel.updateUsuario(
                                                correo = userValue.correo,
                                                password = finalPassword,
                                                run = userValue.run,
                                                nombre = nombre,
                                                numero = numero,
                                                rol = userValue.rol
                                            )
                                            navController.popBackStack()
                                        } ?: throw IllegalStateException("Usuario no disponible para actualizar.")
                                    } catch (e: Exception) {
                                        e.printStackTrace() // Log the error for the developer
                                        snackbarHostState.showSnackbar(
                                            message = "Error al guardar. Inténtalo de nuevo.",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
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
}
