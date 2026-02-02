package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuClienteScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu de usuario") },
                navigationIcon = {
                    Icon(
                        Icons.Default.DirectionsCar,
                        contentDescription = "Logo",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Configuración")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(text = { Text("Configuración de usuario") }, onClick = { navController.navigate("modificar_perfil"); showMenu = false }, leadingIcon = { Icon(Icons.Default.Person, null) })
                        DropdownMenuItem(text = { Text("Contáctanos") }, onClick = { navController.navigate("contacto"); showMenu = false }, leadingIcon = { Icon(Icons.Default.Call, null) })
                        DropdownMenuItem(text = { Text("Políticas de uso") }, onClick = { navController.navigate("politicas_de_uso"); showMenu = false }, leadingIcon = { Icon(Icons.Default.Info, null) })
                        Divider()
                        DropdownMenuItem(text = { Text("Cierre de sesión") }, onClick = { usuarioViewModel.logout(); navController.navigate("login") { popUpTo(0) }; showMenu = false }, leadingIcon = { Icon(Icons.Default.ExitToApp, null) })
                    }
                }
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
            MenuButton(text = "Creación de orden", icon = Icons.Default.AddCircleOutline) { navController.navigate("crear_orden") }
            Spacer(modifier = Modifier.height(24.dp))
            // El nombre original "modificar orden" es ambiguo, lo cambiamos a "Mis Órdenes" que es más claro
            MenuButton(text = "Mis Órdenes", icon = Icons.Default.ListAlt) { navController.navigate("buscar_orden") }
            Spacer(modifier = Modifier.height(24.dp))
            MenuButton(text = "Monitoreo de ordenes", icon = Icons.Default.Timeline) { navController.navigate("buscar_orden") }
        }
    }
}
