package com.example.myapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Servicios")
data class Servicio (
    @PrimaryKey
    val id: Int=0,
    val nombre: String,
    val precio: Double
)
