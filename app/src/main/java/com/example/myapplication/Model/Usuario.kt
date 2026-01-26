package com.example.myapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Usuarios")
data class Usuario (
    @PrimaryKey
    var correo:String,
    var password: String,
    var run: String,
    var nombre: String,
    var numero: String,
    var rol: Boolean
)