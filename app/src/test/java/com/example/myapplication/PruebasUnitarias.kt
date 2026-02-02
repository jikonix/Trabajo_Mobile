package com.example.myapplication

import com.example.myapplication.Model.Usuario
import com.example.myapplication.repository.ApiUsuarios
import com.example.myapplication.Viewmodel.ApiUsuarioModel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class PruebasUnitarias : FunSpec({
    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }

    test("obtenerUsuariosResponse debe cargar usuarios exitosamente") {
        runTest(testDispatcher) {
            // Given
            val mockRepo = mockk<ApiUsuarios>()
            val usuariosEsperados = listOf(
                Usuario("jose@correo.cl", "1234", "222222222", "jose", "123456789", true),
                Usuario("sasha@correo.cl", "1234", "222222222", "sasha", "123456789", true),
                Usuario("juan@correo.cl", "1234", "222222222", "juan", "123456789", true),
                Usuario("maria@correo.cl","1234", "222222222", "maria", "123456789", true)

            )

            coEvery { mockRepo.obtenerUsuarios() } returns Response.success(usuariosEsperados)

            // Pasar el mock al ViewModel
            val viewModel = ApiUsuarioModel(repo = mockRepo)

            // When
            viewModel.obtenerUsuariosResponse()
            testDispatcher.scheduler.advanceUntilIdle()

            // Then
            viewModel.usuarios shouldHaveSize 4
        }
    }
})