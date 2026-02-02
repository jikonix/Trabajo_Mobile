package com.example.myapplication.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Model.Servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOServicios {

    @Query("SELECT * FROM servicios ORDER BY nombre ASC")
    fun getAllServicios(): Flow<List<Servicio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServicio(servicio: Servicio)

    @Query("DELETE FROM servicios WHERE id = :servicioId")
    suspend fun deleteServicio(servicioId: Int)
}
