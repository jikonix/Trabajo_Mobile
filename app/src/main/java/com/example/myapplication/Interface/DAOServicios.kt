package com.example.myapplication.Interface
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Model.Servicio
import com.example.myapplication.Model.Usuario
import kotlinx.coroutines.flow.Flow
@Dao
interface DAOServicios {
    @Insert
    suspend fun ingresarServicio(servicio: Servicio)

    @Query("delete from Servicios where id = :id")
    suspend fun deleteServicio(id: Int)

    @Query("select * from Usuarios")
    fun obtenerServicios(): Flow<List<Usuario>>

    @Query("update Servicios set nombre=:nombre, comentarios=:comentarios, precio =:precio  where id = :id ")
    suspend fun updateServicio(id:Int,nombre: String, comentarios: String, precio:Double)
    //revisar si quedo bien
}