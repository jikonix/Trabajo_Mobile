package com.example.myapplication.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Servicio
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.Model.SolicitudConDetalle
import com.example.myapplication.repository.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class SolicitudViewModel(application: Application) : AndroidViewModel(application) {

    private val solicitudDao = AppDatabase.getDatabase(application).solicitudDao()
    private val servicioDao = AppDatabase.getDatabase(application).servicioDao()

    val todosLosServicios: Flow<List<Servicio>> = servicioDao.getAllServicios()

    // --- Lógica para solicitudes de un usuario específico ---
    private val _correoUsuario = MutableStateFlow<String?>(null)
    val solicitudesDelUsuario: Flow<List<SolicitudConDetalle>> = _correoUsuario.flatMapLatest { correo ->
        if (correo != null) {
            solicitudDao.obtenerSolicitudesPorUsuario(correo)
        } else {
            MutableStateFlow(emptyList())
        }
    }

    // --- Lógica para todas las solicitudes (admin) y horas ocupadas ---
    private val _selectedDate = MutableStateFlow<String?>(null)
    val todasLasSolicitudes: Flow<List<SolicitudConDetalle>> = solicitudDao.getAllSolicitudes()
    val horasOcupadas: Flow<List<String>> = _selectedDate.flatMapLatest { date ->
        if (date != null) {
            solicitudDao.getHorasOcupadasPorFecha(date)
        } else {
            MutableStateFlow(emptyList())
        }
    }

    private val _solicitudEnEdicion = MutableStateFlow<Solicitud?>(null)
    val solicitudEnEdicion = _solicitudEnEdicion.asStateFlow()

    fun cargarSolicitudesDe(correo: String) {
        _correoUsuario.value = correo
    }

    fun onDateSelected(fecha: String) {
        _selectedDate.value = fecha
    }

    fun cargarSolicitudParaEdicion(solicitudId: Int) {
        viewModelScope.launch {
            _solicitudEnEdicion.value = solicitudDao.getSolicitudById(solicitudId)
        }
    }

    fun crearSolicitud(correoUsuario: String, auto: String, patente: String, servicioId: Int, fecha: String, hora: String) {
        viewModelScope.launch {
            val nuevaSolicitud = Solicitud(
                correo = correoUsuario,
                fecha = fecha,
                hora = hora,
                auto = auto,
                patente = patente,
                servicioid = servicioId,
                estado = "Ingresada"
            )
            solicitudDao.insertSolicitud(nuevaSolicitud)
        }
    }

    fun actualizarSolicitud(solicitud: Solicitud) {
        viewModelScope.launch {
            solicitudDao.updateSolicitud(solicitud)
        }
    }

    fun cancelarSolicitud(solicitudId: Int) {
        viewModelScope.launch {
            solicitudDao.deleteSolicitudPorId(solicitudId)
        }
    }

    fun actualizarEstado(solicitudId: Int, nuevoEstado: String) {
        viewModelScope.launch {
            solicitudDao.actualizarEstado(solicitudId, nuevoEstado)
        }
    }
}
