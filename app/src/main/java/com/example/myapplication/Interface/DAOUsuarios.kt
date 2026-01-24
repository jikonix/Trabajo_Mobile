package com.example.myapplication.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Model.Usuario
import kotlinx.coroutines.flow.Flow
@Dao
interface DAOUsuarios {

    @Insert
    suspend fun insert(usuario:Usuario)

    @Query("SELECT * FROM Usuarios WHERE correo = :correo and password = :password")
    suspend fun login(correo: String, password: String): Usuario

    @Query("delete from Usuarios where correo = :correo")
    suspend fun deleteUser(correo:String)

    @Query("select * from Usuarios")
    fun obtenerUsuarios(): Flow<List<Usuario>>

    @Query("update Usuarios set numero=:numero, rol=:rol,run=:run, nombre=:nombre, password = :password where correo = :correo")
    suspend fun updateUsuario(correo: String,password: String,run: String,nombre: String, numero: Number, rol: Boolean)

}