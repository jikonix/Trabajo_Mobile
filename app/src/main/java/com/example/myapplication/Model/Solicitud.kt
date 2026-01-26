package com.example.myapplication.Model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Solicitudes")
data class Solicitud (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val correo:String,
    val fecha: String,
    val auto:String,
    val patente: String,
    val servicioid:Int,
    val estado: String
)