package com.example.myapplication.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Usuario
import com.example.myapplication.repository.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(app: Application) : AndroidViewModel(app) {
    private val usuarioDAO = AppDatabase.getDatabase(app).usuarioDao()
    val usuarios = usuarioDAO.obtenerUsuarios()

    private val _loggedInUser = MutableStateFlow<Usuario?>(null)
    val loggedInUser = _loggedInUser.asStateFlow()

    fun insertarUsuario(correo: String, password: String, run: String, nombre: String, numero: String, rol: Boolean) = viewModelScope.launch {
        val user = Usuario(correo, password, run, nombre, numero, rol)
        usuarioDAO.insert(user)
    }

    suspend fun login(correo: String, password: String): Boolean {
        val user = usuarioDAO.login(correo, password)
        _loggedInUser.value = user
        return user != null
    }

    fun logout() {
        _loggedInUser.value = null
    }

    fun deleteUser(correo: String) = viewModelScope.launch {
        usuarioDAO.deleteUser(correo)
    }

    fun updateUsuario(correo: String, password: String, run: String, nombre: String, numero: String, rol: Boolean) = viewModelScope.launch {
        usuarioDAO.updateUsuario(correo, password, run, nombre, numero, rol)
    }
}