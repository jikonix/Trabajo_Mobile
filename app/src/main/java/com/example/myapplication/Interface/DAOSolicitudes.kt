package com.example.myapplication.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.Model.SolicitudConDetalle
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOSolicitudes {

    @Insert
    suspend fun insertSolicitud(solicitud: Solicitud)

    @Update
    suspend fun updateSolicitud(solicitud: Solicitud)

    @Transaction
    @Query("SELECT * FROM solicitudes WHERE correo = :correoUsuario ORDER BY id DESC")
    fun obtenerSolicitudesPorUsuario(correoUsuario: String): Flow<List<SolicitudConDetalle>>

    @Transaction
    @Query("SELECT * FROM solicitudes ORDER BY id DESC")
    fun getAllSolicitudes(): Flow<List<SolicitudConDetalle>>

    @Query("SELECT hora FROM solicitudes WHERE fecha = :fecha")
    fun getHorasOcupadasPorFecha(fecha: String): Flow<List<String>>

    @Query("SELECT * FROM solicitudes WHERE id = :solicitudId")
    suspend fun getSolicitudById(solicitudId: Int): Solicitud?

    @Query("DELETE FROM solicitudes WHERE id = :solicitudId")
    suspend fun deleteSolicitudPorId(solicitudId: Int)

    @Query("UPDATE solicitudes SET estado = :nuevoEstado WHERE id = :solicitudId")
    suspend fun actualizarEstado(solicitudId: Int, nuevoEstado: String)
}
