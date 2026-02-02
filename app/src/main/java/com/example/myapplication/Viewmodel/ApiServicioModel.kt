package com.example.myapplication.Viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Servicio
import com.example.myapplication.repository.ApiServicios
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class ApiServicioModel (   private val repo: ApiServicios = ApiServicios()) : ViewModel(){
    var servicios = mutableStateListOf<Servicio>()
        private set
    fun obtenerUsuariosResponse() = viewModelScope.launch {
        try {
            val response = repo.obtenerServicios()
            if (response.isSuccessful) {
                val users = response.body() ?: emptyList()
                servicios.clear()
                servicios.addAll(users)
            } else {
                println("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            println("Excepción de red: ${e.message}")
        }
    }

    //Agregar
    fun agregarUsuarioResponse(servicio: Servicio) = viewModelScope.launch {
        val response = repo.agregarServicio(servicio)
        if (response.isSuccessful) {
            println("Usuario agregado exitosamente")
        } else {
            println("Error: ${response.code()} ${response.message()}")
        }
    }
}