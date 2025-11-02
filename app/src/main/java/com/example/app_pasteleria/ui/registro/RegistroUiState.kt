package com.example.app_pasteleria.ui.registro

data class RegistroUiState(
    val correo : String = "",
    val password : String = "",
    val confirmarPassword : String = "",
    val isLoading :Boolean = false,
    val error :String? = null
)