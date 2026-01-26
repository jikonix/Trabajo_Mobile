package com.example.myapplication.Interface
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Model.Servicio
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.Model.Usuario
import kotlinx.coroutines.flow.Flow
@Dao
interface DAOSolicitudes {
    @Insert
    suspend fun ingresarSolicitud(solicitud: Solicitud)

    @Query("delete from solicitudes where id = :id")
    suspend fun deleteSolicitud(id: Int)

    @Query("select * from Solicitudes")
    fun obtenerSolicitudes(): Flow<List<Solicitud>>

    @Query("update Servicios set nombre=:nombre, comentarios=:comentarios, precio =:precio  where id = :id ")
    suspend fun updateSolicitud(id: Int, nombre: String, comentarios: String, precio:Double)

}