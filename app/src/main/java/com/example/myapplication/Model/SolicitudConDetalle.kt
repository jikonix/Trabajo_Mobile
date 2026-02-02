package com.example.myapplication.Model

import androidx.room.Embedded
import androidx.room.Relation

data class SolicitudConDetalle(
    @Embedded
    val solicitud: Solicitud,

    @Relation(
        parentColumn = "servicioid",
        entityColumn = "id"
    )
    val servicio: Servicio
)
