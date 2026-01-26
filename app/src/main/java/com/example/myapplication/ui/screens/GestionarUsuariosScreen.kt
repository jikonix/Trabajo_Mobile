package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Model.Usuario
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestionarUsuariosScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val usuarios by usuarioViewModel.usuarios.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestionar Usuarios") },
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
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("registro") }) {
                Icon(Icons.Default.Add, contentDescription = "Crear Usuario")
            }
        }
    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(usuarios) { usuario ->
                UsuarioCard(usuario = usuario, usuarioViewModel = usuarioViewModel)
            }
        }
    }
}

@Composable
fun UsuarioCard(usuario: Usuario, usuarioViewModel: UsuarioViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = usuario.nombre, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(text = usuario.correo, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = if (usuario.rol) "Admin" else "Cliente",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (usuario.rol) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
            // TODO: Añadir lógica para activar/desactivar el usuario en el ViewModel
            Switch(checked = true, onCheckedChange = { /* Lógica de activación */ })
        }
    }
}
