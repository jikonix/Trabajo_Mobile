package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Model.Usuario
import com.example.myapplication.Viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestionarUsuariosScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val usuarios by usuarioViewModel.usuarios.collectAsState(initial = emptyList())
    var searchQuery by remember { mutableStateOf("") }
    var selectedUser by remember { mutableStateOf<Usuario?>(null) }

    val filteredUsers = usuarios.filter { it.nombre.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Fondo oscuro
        topBar = {
            TopAppBar(
                title = { Text("Deshabilitar un usuario", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscador de Usuario") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    focusedLeadingIconColor = Color.White,
                    unfocusedLeadingIconColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filteredUsers) {
                    usuario ->
                    UsuarioManagementCard(usuario = usuario, onSelect = { selectedUser = usuario })
                }
            }

            if (selectedUser != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Text("A quien desea desahibilitar?", style = MaterialTheme.typography.titleLarge, color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Lógica para confirmar */ }) {
                        Icon(Icons.Default.Check, contentDescription = "Confirmar", tint = Color.Green, modifier = Modifier.size(48.dp))
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    IconButton(onClick = { selectedUser = null }) {
                        Icon(Icons.Default.Close, contentDescription = "Cancelar", tint = Color.Red, modifier = Modifier.size(48.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun UsuarioManagementCard(usuario: Usuario, onSelect: () -> Unit) {
    var deshabilitar by remember { mutableStateOf(false) }
    var borrar by remember { mutableStateOf(false) }
    var modificar by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onSelect),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(usuario.nombre, style = MaterialTheme.typography.bodyLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = deshabilitar, onCheckedChange = { deshabilitar = it })
            Checkbox(checked = borrar, onCheckedChange = { borrar = it })
            Checkbox(checked = modificar, onCheckedChange = { modificar = it })
        }
    }
}
