package com.example.myapplication.Viewmodel
import com.example.myapplication.repository.AppDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Usuario
import kotlinx.coroutines.launch

class UsuarioViewModel(app: Application) : AndroidViewModel(app) {
    private val usuarioDAO = AppDatabase.getDatabase(app).usuarioDao()
    val usuarios = usuarioDAO.obtenerUsuarios()

    fun insertarUsuario(correo: String,password: String,run: String,nombre: String, numero: Number, rol: Boolean) = viewModelScope.launch {
        val user = Usuario(correo,password,run,nombre,numero,rol)
        usuarioDAO.insert(user)
    }
    fun login (correo:String, password:String) = viewModelScope.launch {
        usuarioDAO.login(correo, password)
    }
    fun deleteUser(correo: String) = viewModelScope.launch {
        usuarioDAO.deleteUser(correo)
    }
    fun updateUsuario(correo: String,password: String,run: String,nombre: String, numero: Number, rol: Boolean) = viewModelScope.launch {
        usuarioDAO.updateUsuario(correo,password,run,nombre,numero,rol)
    }
}