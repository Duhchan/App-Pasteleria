package com.example.app_pasteleria.ui.registro


import com.example.app_pasteleria.MainDispatcherExtension
import com.example.app_pasteleria.data.repository.AuthRepository
import com.example.app_pasteleria.ui.registro.RegistroViewModel
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith // <<< Esto es JUnit 5 puro

@ExtendWith(MainDispatcherExtension::class) // Inyectamos la extensión de corrutinas
class RegistroViewModelTest {

    @Test
    fun `si las contraseñas son distintas marca error`() {
        val mockRepo = mockk<AuthRepository>(relaxed = true)
        val viewModel = RegistroViewModel(mockRepo)

        viewModel.onCorreoChange("alumno@duocuc.cl")
        viewModel.onPasswordChange("1234")
        viewModel.onConfirmarPasswordChange("9999") // Diferente


        viewModel.submitRegistro { }
        assertEquals("Las contraseñas no coinciden", viewModel.uiState.error)

        coVerify(exactly = 0) { mockRepo.registro(any(), any()) }
    }
}