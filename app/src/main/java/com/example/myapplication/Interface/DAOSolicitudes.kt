package com.example.myapplication.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.Model.SolicitudConDetalle
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOSolicitudes {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSolicitud(solicitud: Solicitud)

    @Transaction
    @Query("""
        SELECT Solicitudes.*, Servicios.nombre AS nombreServicio 
        FROM Solicitudes 
        INNER JOIN Servicios ON Solicitudes.servicioid = Servicios.id 
        WHERE Solicitudes.correo = :correoUsuario
    """)
    fun obtenerSolicitudesPorUsuario(correoUsuario: String): Flow<List<SolicitudConDetalle>>


    @Query("DELETE FROM Solicitudes WHERE id = :id")
    suspend fun deleteSolicitudPorId(id: Int)


    @Query("UPDATE Solicitudes SET estado = :nuevoEstado WHERE id = :id")
    suspend fun actualizarEstado(id: Int, nuevoEstado: String)
}