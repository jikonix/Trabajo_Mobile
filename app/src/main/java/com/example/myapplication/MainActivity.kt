package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.*
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.Viewmodel.UsuarioViewModel
import com.example.myapplication.Viewmodel.ServicioViewModel
import com.example.myapplication.Viewmodel.SolicitudViewModel

class MainActivity : ComponentActivity() {
    private val usuarioViewModel: UsuarioViewModel by viewModels()
    private val servicioViewModel: ServicioViewModel by viewModels()
    private val solicitudViewModel: SolicitudViewModel by viewModels()

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
                    composable("gestionar_servicios") {
                        GestionarServiciosScreen(navController = navController, servicioViewModel = servicioViewModel)
                    }
                    composable("crear_orden") {
                        CrearOrdenScreen(navController = navController, usuarioViewModel = usuarioViewModel, solicitudViewModel = solicitudViewModel)
                    }
                    composable("buscar_orden") {
                        BuscarOrdenScreen(navController = navController, usuarioViewModel = usuarioViewModel, solicitudViewModel = solicitudViewModel)
                    }
                    composable(
                        route = "modificar_orden/{solicitudId}",
                        arguments = listOf(navArgument("solicitudId") { type = NavType.IntType })
                    ) {
                        backStackEntry ->
                        val solicitudId = backStackEntry.arguments?.getInt("solicitudId") ?: 0
                        ModificarOrdenScreen(
                            navController = navController,
                            solicitudId = solicitudId,
                            solicitudViewModel = solicitudViewModel
                        )
                    }
                    composable("contacto") {
                        ContactoScreen(navController = navController)
                    }
                    composable("politicas_de_uso") {
                        PoliticasDeUsoScreen(navController = navController)
                    }
                    composable("gestionar_ordenes") {
                        GestionarOrdenesScreen(navController = navController, solicitudViewModel = solicitudViewModel)
                    }
                }
            }
        }
    }
}
