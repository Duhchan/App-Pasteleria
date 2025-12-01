package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_pasteleria.data.repository.AuthRepository
import com.example.app_pasteleria.ui.login.LoginViewModel
import com.example.app_pasteleria.ui.registro.RegistroViewModel

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}