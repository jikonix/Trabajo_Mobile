package com.example.myapplication.repository
import com.example.myapplication.Model.Usuario
import retrofit2.Response
class ApiUsuarios {
    //Obtener todos los usuarios
    suspend fun obtenerUsuarios():Response<List<Usuario>> {
        return RetrofitClient.apiService.getUsers()

    }
    //Obtener un usuario por su nombre de usuario
    suspend fun obtenerUsuario(correo: String): Response<Usuario> {
        return RetrofitClient.apiService.getUser(correo)
    }

    //Agregar un usuario
    suspend fun agregarUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitClient.apiService.createUser(usuario)
    }

    //Eliminar un usuari
    suspend fun eliminarUsuario(correo: String): Response<Usuario> {
        return RetrofitClient.apiService.deleteUser(correo)
    }

    //Actualizar un usuario
    suspend fun actualizarUsuario(correo: String, usuario: Usuario): Response<Usuario> {
        return RetrofitClient.apiService.updateUser(correo, usuario)
    }
}