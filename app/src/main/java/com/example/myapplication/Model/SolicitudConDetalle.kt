package com.example.myapplication.Model

import androidx.room.Embedded

data class SolicitudConDetalle(
    // Trae todos los campos de la tabla Solicitud
    @Embedded val solicitud: Solicitud,

    // Campo extra que viene del JOIN con la tabla Servicios
    val nombreServicio: String
)