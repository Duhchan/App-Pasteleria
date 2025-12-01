package com.example.app_pasteleria.ui.registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.data.repository.AuthRepository
import kotlinx.coroutines.launch


class RegistroViewModel(
    private val repo: AuthRepository): ViewModel() {
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

        viewModelScope.launch { // <--- Lanzamos corrutina
            val exito = repo.registro(uiState.correo.trim(), uiState.password)

            uiState = uiState.copy(isLoading = false, error = null)

            if (exito) {
                onSucces(uiState.correo.trim())
            } else {
                uiState = uiState.copy(error = "Error: El usuario ya existe o datos inválidos", isLoading = false)
            }
        }
    }
}