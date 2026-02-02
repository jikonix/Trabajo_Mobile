package com.example.myapplication.Viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.repository.ApiSolicitud
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class ApiSolicitudModel (   private val repo: ApiSolicitud = ApiSolicitud()) : ViewModel(){
    var solicitudes = mutableStateListOf<Solicitud>()
        private set
    fun obtenerUsuariosResponse() = viewModelScope.launch {
        try {
            val response = repo.obtenerSolicitudes()
            if (response.isSuccessful) {
                val users = response.body() ?: emptyList()
                solicitudes.clear()
                solicitudes.addAll(users)
            } else {
                println("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            println("Excepción de red: ${e.message}")
        }
    }

    //Agregar
    fun agregarUsuarioResponse(solicitud: Solicitud) = viewModelScope.launch {
        val response = repo.agregarSolicitud(solicitud)
        if (response.isSuccessful) {
            println("Usuario agregado exitosamente")
        } else {
            println("Error: ${response.code()} ${response.message()}")
        }
    }
}