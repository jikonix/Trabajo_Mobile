package com.example.myapplication.repository

import com.example.myapplication.Model.Servicio
import retrofit2.Response
class ApiServicios {
    suspend fun obtenerServicios():Response<List<Servicio>> {
        return RetrofitClient.apiService.getServices()

    }
    //Obtener servicio
    suspend fun obtenerServicio(id: Int): Response<Servicio> {
        return RetrofitClient.apiService.getService(id)
    }

    //Agregar un servicio
    suspend fun agregarServicio(servicio: Servicio): Response<Servicio> {
        return RetrofitClient.apiService.createServices(servicio)
    }

    //Eliminar un servicio
    suspend fun eliminarServicio(id: Int): Response<Servicio> {
        return RetrofitClient.apiService.deleteServices(id)
    }

    //Actualizar un servicio
    suspend fun actualizarServicio(id: Int, servicio: Servicio): Response<Servicio> {
        return RetrofitClient.apiService.updateServices(id, servicio)
    }
}