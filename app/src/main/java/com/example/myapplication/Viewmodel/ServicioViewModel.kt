package com.example.myapplication.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Servicio
import com.example.myapplication.repository.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ServicioViewModel(application: Application) : AndroidViewModel(application) {

    private val servicioDao = AppDatabase.getDatabase(application).servicioDao()

    val todosLosServicios: Flow<List<Servicio>> = servicioDao.getAllServicios()

    fun insertarServicio(nombre: String, precio: Double) {
        if (nombre.isNotBlank() && precio > 0) {
            viewModelScope.launch {
                val nuevoServicio = Servicio(nombre = nombre, precio = precio)
                servicioDao.insertServicio(nuevoServicio)
            }
        }
    }

    fun eliminarServicio(servicioId: Int) {
        viewModelScope.launch {
            servicioDao.deleteServicio(servicioId)
        }
    }
}
