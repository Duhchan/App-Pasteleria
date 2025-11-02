package com.example.app_pasteleria.ui.registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app_pasteleria.data.repository.AuthRepository


class RegistroViewModel(
    private val repo: AuthRepository = AuthRepository()

): ViewModel() {
    var uiState by mutableStateOf(RegistroUiState())
    fun onCorreoChange(correo: String) {
        uiState = uiState.copy(correo = correo, error = null)
    }//fin de correoChange

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password, error = null)
    }//fin de passwordChange

    fun onConfirmarPasswordChange(confirmarPassword: String) {
        uiState = uiState.copy(confirmarPassword = confirmarPassword, error = null)
    } //fin del confirmarPaswword

    fun submitRegistro(onSucces: (String) -> Unit) {
        uiState = uiState.copy(isLoading = true, error = null)

        if (uiState.password != uiState.confirmarPassword) {
            uiState = uiState.copy(error = "Las contraseñas no coinciden", isLoading = false)
            return
        }
        val exito = repo.Registro(uiState.correo.trim(), uiState.password)

        if (exito) {
            onSucces(" Registro exitoso")
        } else {
            uiState = uiState.copy(error = "Error de registro", isLoading = false)
        }


    }
}