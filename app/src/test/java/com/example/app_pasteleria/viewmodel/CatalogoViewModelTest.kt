package com.example.app_pasteleria

import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
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
    fun `se guarda correctamente el pastel`() = runTest {

        val mockRepo = mockk<CatalogoRepository>(relaxed = true)
        val viewModel = CatalogoViewModel(mockRepo)

        val pastelNuevo = Catalogo(
            nombre = "Prueba Unitaria",
            precio = "$1000",
            descripcion = "Torta de test",
            imagen = 0
        )

        viewModel.guardarPastel(pastelNuevo)

        advanceUntilIdle()
        coVerify { mockRepo.insertarCatalogo(pastelNuevo) }
    }
}