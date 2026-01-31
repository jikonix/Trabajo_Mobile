package com.example.myapplication.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "solicitudes",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["correo"],
            childColumns = ["correo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Servicio::class,
            parentColumns = ["id"],
            childColumns = ["servicioid"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Solicitud(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val correo: String,
    val fecha: String,
    val hora: String, // Nuevo campo para la hora
    val auto: String,
    val patente: String,
    val servicioid: Int,
    val estado: String
)
