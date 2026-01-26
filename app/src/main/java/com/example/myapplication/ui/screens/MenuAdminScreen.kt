package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.* // Import all default icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAdminScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val user by usuarioViewModel.loggedInUser.collectAsState()

    val adminMenuItems = listOf(
        MenuItem("Gestionar Usuarios", Icons.Default.Group, "gestionar_usuarios"),
        MenuItem("Gestionar Órdenes", Icons.Default.ListAlt, "gestionar_ordenes"),
        MenuItem("Gestionar Servicios", Icons.Default.MiscellaneousServices, "gestionar_servicios"),
        MenuItem("Modificar Perfil", Icons.Default.Person, "modificar_perfil")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Administrador") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = {
                        usuarioViewModel.logout()
                        navController.navigate("login") {
                            popUpTo(0) // Limpia todo el back stack
                        }
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            user?.let {
                Text("Hola, ${it.nombre} (Admin)", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(24.dp))
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(adminMenuItems) { item ->
                    MenuItemCard(item = item, navController = navController)
                }
            }
        }
    }
}
