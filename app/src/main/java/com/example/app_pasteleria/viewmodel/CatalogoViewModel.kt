package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel(private val repository: CatalogoRepository) : ViewModel(){
    private val _pasteles = MutableStateFlow<List<Catalogo>>(emptyList())
    val pasteles: StateFlow<List<Catalogo>> = _pasteles.asStateFlow()

    init { obtenerProductos() }
    fun guardarPastel(pastel: Catalogo){
        viewModelScope.launch {
            repository.insertarCatalogo(pastel)
        }
    } // fin guardarProducto

    // hacer el listado de productos(pasteles)
    fun obtenerProductos(){
        viewModelScope.launch {
            repository.obtenerProductos().collect{ // se reciben datos del flow
                listaProductos -> _pasteles.value = listaProductos
            }
        }
    }
} // fin class
