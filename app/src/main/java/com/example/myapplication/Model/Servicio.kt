package com.example.myapplication.Model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Servicios")
data class Servicio (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val nombre: String,
    val comentarios: String,
    val precio:Double
)