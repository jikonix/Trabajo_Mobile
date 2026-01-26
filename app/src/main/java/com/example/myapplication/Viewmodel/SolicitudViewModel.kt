package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.Model.SolicitudConDetalle // Importante: La clase combinada
import com.example.myapplication.repository.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SolicitudViewModel(app: Application) : AndroidViewModel(app) {

    private val solicitudDao = AppDatabase.getDatabase(app).solicitudDao()


    fun obtenerHistorial(correoCliente: String): Flow<List<SolicitudConDetalle>> {
        return solicitudDao.obtenerSolicitudesPorUsuario(correoCliente)
    }

    fun guardarSolicitud(
        correo: String,
        fecha: String,
        auto: String,
        patente: String,
        servicioId: Int, // Recibimos el ID, pero en la lista mostraremos el Nombre gracias al JOIN
        estado: String = "Pendiente"
    ) {
        viewModelScope.launch {
            val nuevaSolicitud = Solicitud(
                correo = correo,
                fecha = fecha,
                auto = auto,
                patente = patente,
                servicioid = servicioId,
                estado = estado
            )

            solicitudDao.insertSolicitud(nuevaSolicitud)
        }
    }

    fun eliminarSolicitud(id: Int) = viewModelScope.launch {
        solicitudDao.deleteSolicitudPorId(id)
    }

    // actualizar estado
    fun actualizarEstadoSolicitud(id: Int, nuevoEstado: String) = viewModelScope.launch {
        solicitudDao.actualizarEstado(id, nuevoEstado)
    }
}