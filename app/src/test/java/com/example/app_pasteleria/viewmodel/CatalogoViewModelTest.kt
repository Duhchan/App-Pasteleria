package com.example.app_pasteleria.viewmodel

import com.example.app_pasteleria.MainDispatcherExtension
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherExtension::class)
class CatalogoViewModelTest {

    @Test
    fun `guardarPastel llama al repositorio para subir a la nube`() = runTest {

        val mockRepo = mockk<CatalogoRepository>(relaxed = true)

        coEvery { mockRepo.obtenerTortasDeInternet() } returns emptyList()
        coEvery { mockRepo.obtenerCarritoNube() } returns emptyList()


        val viewModel = CatalogoViewModel(mockRepo)

        val pastelNuevo = Catalogo(
            nombre = "Torta Test",
            precio = 5000,
            descripcion = "Descripción de prueba",
            imagen = 0,
            cantidad = 1,
            comentario = ""
        )

        viewModel.guardarPastel(pastelNuevo)

        advanceUntilIdle()

        coVerify { mockRepo.agregarAlCarritoNube(pastelNuevo) }
    }
}