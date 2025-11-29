package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CatalogoViewModel(private val repository: CatalogoRepository) : ViewModel() {

    //LISTA DE PEDIDOS (Base de Datos Local)
    private val _pedidos = MutableStateFlow<List<Catalogo>>(emptyList())
    val pedidos: StateFlow<List<Catalogo>> = _pedidos.asStateFlow()

    //MENÚ DE TORTAS (Traído de la API)
    private val _menuTortas = MutableStateFlow<List<Catalogo>>(emptyList())
    val menuTortas: StateFlow<List<Catalogo>> = _menuTortas.asStateFlow()


    private val _recordarEntrega = MutableStateFlow(false)
    val recordarEntrega: StateFlow<Boolean> = _recordarEntrega.asStateFlow()

    init {
        // Al iniciar, cargamos los pedidos guardados y el menú de internet
        viewModelScope.launch {
            repository.obtenerProductos().collect { listaPedidos ->
                _pedidos.value = listaPedidos
            }
        }
        cargarMenuDesdeApi()
    }

    private fun cargarMenuDesdeApi() {
        viewModelScope.launch {
            // Aquí llamamos al repositorio que baja la lista de npoint.io
            val listaApi = repository.obtenerTortasDeInternet()
            _menuTortas.value = listaApi
        }
    }

    // guarda en la Base de Datos (CONFIRMAR PEDIDO)
    fun guardarPastel(pastel: Catalogo) {
        viewModelScope.launch {
            repository.insertarCatalogo(pastel)
        }
    }

    fun cambiarEstadoEntregado() {
        _recordarEntrega.value = true
    }

    fun limpiarPedidos() {
        viewModelScope.launch {
            repository.eliminarCatalogo()
        }
    }

}