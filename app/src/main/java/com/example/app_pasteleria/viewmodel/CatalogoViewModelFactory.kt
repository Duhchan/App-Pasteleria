package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_pasteleria.data.repository.CatalogoRepository

class CatalogoViewModelFactory(private val repository: CatalogoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}