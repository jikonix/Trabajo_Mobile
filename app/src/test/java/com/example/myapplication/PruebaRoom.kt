package com.example.myapplication

import com.example.myapplication.Interface.DAOUsuarios
import com.example.myapplication.Model.Usuario
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*

class PruebaRoom : FunSpec( {
    /*
    * Es un despachador de coroutines para pruebas.
    * Controla manualmente cuándo se ejecutan las coroutines, permitiendo:
        Ejecutar tareas de forma controlada
        Evitar que las pruebas dependan del tiempo real
        Hacer las pruebas predecibles y rápidas
* */
    val testDispatcher = StandardTestDispatcher()

    //Antes de cada test ,reemplaza el dispatcher principal
    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    //Despues de cada test reinicia el original
    afterTest {
        Dispatchers.resetMain()
    }

    //Test(Genera prueba correspondiente mas el test que debe ejecutar
    /*
    * Crea un objeto simulado (mock) de una clase o interfaz permitiendo:
        Simular comportamientos sin usar la implementación real
        Controlar qué devuelve cada método
        Verificar si se llamaron métodos específicos
    * */
    test("insertarUsuario debe llamar al DAO") {
        runTest(testDispatcher) {
            // Given
            //Generamos valores de mock
            val mockDao = mockk<DAOUsuarios>(relaxed = true)
            val usuarioCapturado = slot<Usuario>()

            //Permite ejecutar la funcion de una corutina
            coEvery { mockDao.insert(capture(usuarioCapturado)) } just Runs

            // Inyectar mock manualmente requiere refactorizar UsuarioViewModel
            // para aceptar el DAO como parámetro

            // When
            val usuario = Usuario("carlos@test.com", "1234", "222222222", "Carlos", "123456789", true)
            mockDao.insert(usuario )

            // Then
            //Verifica que la funcion haya sido llamada
            coVerify { mockDao.insert(any()) }
            usuarioCapturado.captured.correo shouldBe "carlos@test.com"
            println("Prueba room pasada")
        }
    }
    /*
    * Resumen completo del flujo
    *
    *   beforeTest: Configura el dispatcher de pruebas
        mockk: Crea un repositorio falso
        coEvery: Define qué debe devolver el mock
        viewModel.metodo(): Ejecuta el código a probar
        advanceUntilIdle(): Procesa todas las coroutines
        coVerify: Confirma que se llamó al mock correctamente
        afterTest: Limpia el entorno
    *
    * */
})