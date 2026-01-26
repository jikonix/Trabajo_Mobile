package com.example.myapplication.Viewmodel


import com.example.myapplication.repository.AppDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Servicio
import kotlinx.coroutines.launch

class ServicioViewModel(app: Application) : AndroidViewModel(app) {
    private val servicioDAO = AppDatabase.getDatabase(app).servicioDao()
    val servicios = servicioDAO.obtenerServicios()

    fun ingresarServicio(id: Int,nombre: String, comentarios: String, precio: Double) = viewModelScope.launch {
        val user = Servicio(id,nombre, comentarios, precio)
        servicioDAO.ingresarServicio(user)
    }
    fun deleteServicio(id: Int) = viewModelScope.launch {
        servicioDAO.deleteServicio(id)
    }


    fun updateServicio(id: Int, nombre: String, comentarios: String, precio: Double) = viewModelScope.launch {
        servicioDAO.updateServicio(id,nombre, comentarios, precio)
    }
}