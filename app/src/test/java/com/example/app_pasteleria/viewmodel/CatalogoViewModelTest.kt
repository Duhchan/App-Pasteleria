package com.example.app_pasteleria.viewmodel // Asegúrate de que el package sea correcto

import com.example.app_pasteleria.MainDispatcherExtension
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherExtension::class)
class CatalogoViewModelTest {

    @Test
    fun `se guarda correctamente el pastel`() = runTest {
        // 1. Mockear el repositorio
        val mockRepo = mockk<CatalogoRepository>(relaxed = true)

        // 2. IMPORTANTE: Configurar el flow para que el init del ViewModel no falle
        // Como el ViewModel llama a obtenerProductos() al iniciarse, debemos decirle qué devolver.
        coEvery { mockRepo.obtenerProductos() } returns flowOf(emptyList())

        // 3. Instanciar el ViewModel (ahora es seguro hacerlo)
        val viewModel = CatalogoViewModel(mockRepo)

        // 4. Crear el objeto con los datos correctos
        val pastelNuevo = Catalogo(
            nombre = "Prueba Unitaria",
            precio = 1000, // <--- CORREGIDO: Antes tenías "$1000" (String), ahora es 1000 (Int)
            descripcion = "Torta de test",
            imagen = 0,
            cantidad = 1,
            comentario = "Test"
        )

        // 5. Ejecutar la acción
        viewModel.guardarPastel(pastelNuevo)

        // 6. Esperar a que las corrutinas terminen
        advanceUntilIdle()

        // 7. Verificar que se llamó al repositorio con el objeto correcto
        coVerify { mockRepo.insertarCatalogo(pastelNuevo) }
    }
}