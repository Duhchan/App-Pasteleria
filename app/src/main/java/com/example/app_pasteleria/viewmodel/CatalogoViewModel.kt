package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.data.model.Catalogo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel(){
    private val _pasteles = MutableStateFlow<List<Catalogo>>(emptyList())
    val pasteles: StateFlow<List<Catalogo>> = _pasteles.asStateFlow()

    fun guardarProducto(producto: Catalogo){
        viewModelScope.launch {
            // guardar en memoria

            val nuevaLista = _pasteles.value + producto
            _pasteles.value = nuevaLista
        }

    } // fin guardarProducto
} // fin class
