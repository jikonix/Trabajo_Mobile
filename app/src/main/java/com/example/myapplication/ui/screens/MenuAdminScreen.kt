package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAdminScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Fondo oscuro
        topBar = {
            TopAppBar(
                title = { Text("Menu de Administrador", color = Color.White) },
                navigationIcon = {
                    Icon(
                        Icons.Default.Shield, // Icono de logo para admin
                        contentDescription = "Logo Admin",
                        tint = Color.White,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Configuración", tint = Color.White)
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(
                            text = { Text("Modificar Perfil") },
                            onClick = {
                                navController.navigate("modificar_perfil")
                                showMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Contáctanos") },
                            onClick = {
                                navController.navigate("contacto")
                                showMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.Call, contentDescription = null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Políticas de uso") },
                            onClick = {
                                navController.navigate("politicas_de_uso")
                                showMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) }
                        )
                        Divider()
                        DropdownMenuItem(
                            text = { Text("Cierre de sesión") },
                            onClick = {
                                usuarioViewModel.logout()
                                navController.navigate("login") { popUpTo(0) }
                                showMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.ExitToApp, contentDescription = null) }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Re-using the MenuButton from the client screen for consistency
            MenuButton(text = "Gestionar Usuarios") { navController.navigate("gestionar_usuarios") }
            Spacer(modifier = Modifier.height(24.dp))
            MenuButton(text = "Gestionar Órdenes") { navController.navigate("gestionar_ordenes") }
            Spacer(modifier = Modifier.height(24.dp))
            MenuButton(text = "Gestionar Servicios") { navController.navigate("gestionar_servicios") }
        }
    }
}

// NOTE: This is a duplicate of the MenuButton in MenuClienteScreen. For better practice, this could be moved to a shared file.
@Composable
private fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
