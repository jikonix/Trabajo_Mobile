package com.example.myapplication.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.SolicitudViewModel
import com.example.myapplication.Viewmodel.UsuarioViewModel

@Composable
fun BuscarOrdenScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    solicitudViewModel: SolicitudViewModel
) {
    Text(text = "Buscar Orden Screen")
}