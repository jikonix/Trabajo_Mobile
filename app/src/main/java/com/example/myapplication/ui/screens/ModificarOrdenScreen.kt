package com.example.myapplication.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.Viewmodel.SolicitudViewModel

@Composable
fun ModificarOrdenScreen(
    navController: NavController,
    solicitudId: Int,
    solicitudViewModel: SolicitudViewModel
) {
    Text(text = "Modificar Orden Screen: $solicitudId")
}