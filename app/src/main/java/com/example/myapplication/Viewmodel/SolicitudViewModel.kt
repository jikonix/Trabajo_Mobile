package com.example.myapplication.Viewmodel

import com.example.myapplication.repository.AppDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Solicitud
import kotlinx.coroutines.launch

class SolicitudViewModel (app: Application) : AndroidViewModel(app) {
    private val solicitudDAO = AppDatabase.getDatabase(app).solicitudDao()
    val solicitudes = solicitudDAO.obtenerSolicitudes()

    fun ingresarSolicitud(id: Int,correo: String, fecha:String,auto:String,patente:String,servicioid:Int,estado:String) = viewModelScope.launch {
        val user = Solicitud(id,correo, fecha, auto, patente, servicioid, estado)
        solicitudDAO.ingresarSolicitud(user)
    }
    fun deleteSolicitud(id: Int) = viewModelScope.launch {
        solicitudDAO.deleteSolicitud(id)
    }


    fun updateSolicitud(id: Int, nombre: String, comentarios: String, precio: Double) = viewModelScope.launch {
        solicitudDAO.updateSolicitud(id,nombre, comentarios, precio)

    }
}