package com.example.myapplication.repository
import com.example.myapplication.Model.Usuario
import retrofit2.Response
class ApiUsuarios {
    //Obtener todos los usuarios
    suspend fun obtenerUsuarios():Response<List<Usuario>> {
        return RetrofitClient.apiService.getUsers()

    }
    //Obtener un usuario por su nombre de usuario
    suspend fun obtenerUsuario(username: String): Response<Usuario> {
        return RetrofitClient.apiService.getUser(username)
    }

    //Agregar un usuario
    suspend fun agregarUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitClient.apiService.createUser(usuario)
    }

    //Eliminar un usuari
    suspend fun eliminarUsuario(username: String): Response<Usuario> {
        return RetrofitClient.apiService.deleteUser(username)
    }

    //Actualizar un usuario
    suspend fun actualizarUsuario(username: String, usuario: Usuario): Response<Usuario> {
        return RetrofitClient.apiService.updateUser(username, usuario)
    }
}