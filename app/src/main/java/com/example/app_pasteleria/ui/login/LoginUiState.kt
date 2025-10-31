package com.example.app_pasteleria.ui.login

//Sirve para manejar los datos de la UI que necesita mostrar o controlar
data class LoginUiState(
    val correo :String ="",
    val password :String ="",
    val isLoading :Boolean = false,
    val error :String? = null //maneja nulos

)//fin