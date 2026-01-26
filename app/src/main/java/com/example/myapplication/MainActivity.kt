package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.*
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.Viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    private val usuarioViewModel: UsuarioViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login", modifier = Modifier.fillMaxSize()) {
                    composable("login") {
                        LoginScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                    }
                    composable("registro") {
                        RegistroScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                    }
                    composable("menu_cliente") {
                        MenuClienteScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                    }
                    composable("modificar_perfil") {
                        ModificarPerfilScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                    }
                    composable("menu_admin") {
                        MenuAdminScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                    }
                    composable("gestionar_usuarios") {
                        GestionarUsuariosScreen(navController = navController, usuarioViewModel = usuarioViewModel)
                    }
                    // Pantallas de marcador de posición
                    composable("crear_orden") {
                        PlaceholderScreen(navController = navController, screenTitle = "Crear Orden")
                    }
                    composable("buscar_orden") {
                        PlaceholderScreen(navController = navController, screenTitle = "Buscar Órdenes")
                    }
                    composable("gestionar_ordenes") {
                        PlaceholderScreen(navController = navController, screenTitle = "Gestionar Órdenes")
                    }
                    composable("gestionar_servicios") {
                        PlaceholderScreen(navController = navController, screenTitle = "Gestionar Servicios")
                    }
                }
            }
        }
    }
}
