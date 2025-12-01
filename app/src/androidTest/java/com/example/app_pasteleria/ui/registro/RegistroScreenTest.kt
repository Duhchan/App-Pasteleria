package com.example.app_pasteleria.ui.registro

import RegistroScreen
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app_pasteleria.data.repository.AuthRepository // <--- Asegúrate de importar esto
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistroScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorContrasenas_MuestraMensajeErrorUI() {



        composeTestRule.setContent {
            val navController = rememberNavController()

            // 2. Le pasamos nuestro viewModel ya creado a la pantalla
            RegistroScreen(
                navController = navController,
                vm = viewModel // <--- Aquí pasamos la instancia manual
            )
        }

        // El resto del test sigue igual...
        composeTestRule.onNodeWithTag("registroCorreo")
            .performClick()
            .performTextReplacement("usuario@prueba.cl")

        composeTestRule.onNodeWithTag("registroPass")
            .performClick()
            .performTextReplacement("1234")

        composeTestRule.onNodeWithTag("registroConfirmPass")
            .performClick()
            .performTextReplacement("5678")

        composeTestRule.onNodeWithTag("botonRegistrar")
            .performClick()

        composeTestRule.onNodeWithText("Las contraseñas no coinciden")
            .assertIsDisplayed()
    }
}