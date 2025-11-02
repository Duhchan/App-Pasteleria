package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.R
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel(private val repository: CatalogoRepository) : ViewModel(){


    private val _pasteles = MutableStateFlow<List<Catalogo>>(emptyList())
    val pasteles: StateFlow<List<Catalogo>> = _pasteles.asStateFlow()

    private val _recordarEntrega = MutableStateFlow(false)
    val recordarEntrega: StateFlow<Boolean> = _recordarEntrega.asStateFlow()


    init {
        viewModelScope.launch{
            repository.eliminarCatalogo()
            obtenerProductos()
        }

        }
    fun guardarPastel(pastel: Catalogo){
        viewModelScope.launch {
            repository.insertarCatalogo(pastel)
        }
    } // fin guardarProducto

    fun cambiarEstadoEntregado(){
        viewModelScope.launch {
            _recordarEntrega.value = true
        }
    }

    fun obtenerProductos(){
        viewModelScope.launch {
            repository.obtenerProductos().collect{ // se reciben datos del flow
                listaProductos -> _pasteles.value = listaProductos
            }
        }
    }

    private fun agregarDatosDePrueba() {
        viewModelScope.launch {
            delay(500)
            if (_pasteles.value.isEmpty()) {
                guardarPastel(Catalogo(
                    nombre = "Torta de Chocolate",
                    precio = "45000",
                    descripcion = "Deliciosa torta de chocolate",
                    imagen = R.drawable.tortachocolate
                ))
                guardarPastel(Catalogo(
                    nombre = "Torta de Frutas",
                    precio = "38000",
                    descripcion = "Torta con frutas frescas"
                ))
                guardarPastel(Catalogo(
                    nombre = "Torta de Vainilla",
                    precio = "35000",
                    descripcion = "Clásica torta de vainilla"
                ))
            }
        }
    }



} // fin class
