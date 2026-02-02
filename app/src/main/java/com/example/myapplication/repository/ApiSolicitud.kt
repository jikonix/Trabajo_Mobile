package com.example.myapplication.repository

import com.example.myapplication.Model.Solicitud

import retrofit2.Response

class ApiSolicitud {
    suspend fun obtenerSolicitudes():Response<List<Solicitud>> {
        return RetrofitClient.apiService.getSolis()

    }
    //Obtener un usuario por su nombre de usuario
    suspend fun obtenerSolicitud(id: Int): Response<Solicitud> {
        return RetrofitClient.apiService.getSoli(id)
    }

    //Agregar un usuario
    suspend fun agregarSolicitud(solicitud: Solicitud): Response<Solicitud> {
        return RetrofitClient.apiService.createSoli(solicitud)
    }

    //Eliminar un usuari
    suspend fun eliminarSolicitud(id: Int): Response<Solicitud> {
        return RetrofitClient.apiService.deleteSoli(id)
    }

    //Actualizar un usuario
    suspend fun actualizarSolicitud(id: Int, solicitud: Solicitud): Response<Solicitud> {
        return RetrofitClient.apiService.updateSoli(id, solicitud)
    }
}