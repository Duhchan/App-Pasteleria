package com.example.app_pasteleria.login

import com.example.app_pasteleria.MainDispatcherExtension
import com.example.app_pasteleria.data.repository.AuthRepository
import com.example.app_pasteleria.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherExtension::class)
class LoginViewModelTest {

    @Test
    fun `si es falso se devuelve error`() {

        val mockRepo = mockk<AuthRepository>()

        coEvery { mockRepo.login(any(), any()) } returns false

        val viewModel = LoginViewModel(mockRepo)
        viewModel.onCorreoChange("test@duoc.cl")
        viewModel.onPasswordChange("1234")

        viewModel.submit { }
        assertEquals("Credenciales Inválidas", viewModel.uiState.error)
        assertFalse(viewModel.uiState.isLoading)
    }
}