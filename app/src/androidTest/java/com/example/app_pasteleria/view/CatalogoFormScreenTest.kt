package com.example.app_pasteleria.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogoFormScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ingresarCodigoFelices50_MuestraAlertaDescuento() {
        // 1. Crear ViewModel falso para evitar errores de base de datos
        val fakeDao = object : CatalogoDao {
            override suspend fun insertarPastel(pastel: Catalogo) {}
            override fun obtenerCatalogo(): Flow<List<Catalogo>> = flowOf(emptyList())
            override suspend fun eliminarCatalogo() {}
        }
        val fakeRepo = CatalogoRepository(fakeDao)
        val viewModel = CatalogoViewModel(fakeRepo)

        // 2. Cargar la pantalla
        composeTestRule.setContent {
            val navController = rememberNavController()
            CatalogoFormScreen(
                navController = navController,
                nombre = "Pastel de Prueba",
                precio = "10000",
                descripcion = "Descripción de prueba",
                viewModel = viewModel
            )
        }

        // 3. Interactuar con la pantalla usando las ETIQUETAS que pusimos en el Paso 1

        // Escribir cantidad "1"
        composeTestRule.onNodeWithTag("inputCantidad")
            .performClick()
            .performTextReplacement("1")

        // Escribir código "FELICES50"
        composeTestRule.onNodeWithTag("inputPromocion")
            .performClick()
            .performTextReplacement("FELICES50")

        // Clic en el botón
        composeTestRule.onNodeWithTag("botonAgregar")
            .performClick()

        // 4. Verificar que aparezca la alerta de promoción
        composeTestRule.onNodeWithText("¡Promoción Activada!")
            .assertIsDisplayed()
    }
}