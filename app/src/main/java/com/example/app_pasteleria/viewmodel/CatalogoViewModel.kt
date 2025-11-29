package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.R
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.repository.CatalogoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel(private val repository: CatalogoRepository) : ViewModel() {

    // 1. LISTA DE PEDIDOS (Lo que compras - Base de Datos)
    private val _pedidos = MutableStateFlow<List<Catalogo>>(emptyList())
    val pedidos: StateFlow<List<Catalogo>> = _pedidos.asStateFlow()

    // 2. MENÚ DE TORTAS (Lo que eliges - API Internet)
    // --- ESTA ES LA VARIABLE QUE TE FALTA Y CAUSA EL ERROR ---
    private val _menuTortas = MutableStateFlow<List<Catalogo>>(emptyList())
    val menuTortas: StateFlow<List<Catalogo>> = _menuTortas.asStateFlow()

    // 3. REGALO (Lógica antigua)
    private val _recordarEntrega = MutableStateFlow(false)
    val recordarEntrega: StateFlow<Boolean> = _recordarEntrega.asStateFlow()

    init {
        // Limpiamos base de datos al inicio para evitar duplicados en "Pedidos"
        viewModelScope.launch {
            repository.eliminarCatalogo()
            repository.obtenerProductos().collect { listaPedidos ->
                _pedidos.value = listaPedidos
            }
        }
        // Cargamos las tortas de internet
        cargarMenuDesdeApi()
    }

    private fun cargarMenuDesdeApi() {
        viewModelScope.launch {
            val listaApi = repository.obtenerTortasDeInternet()
            _menuTortas.value = listaApi
        }
    }

    // Valida si damos el regalo (Solo una vez)
    fun validarRegalo(correo: String) {
        val esCorreoDuoc = correo.lowercase().contains("duocuc.cl") ||
                correo.lowercase().contains("profesor@duoc.cl")

        if (esCorreoDuoc) {
            viewModelScope.launch {
                val yaTieneRegalo = _pedidos.value.any { it.nombre == "Torta Regalo Duoc" }
                if (!yaTieneRegalo) {
                    val regalo = Catalogo(
                        nombre = "Torta Regalo Duoc",
                        precio = "0",
                        descripcion = "¡Felicidades! Tienes una torta de regalo por ser comunidad Duoc.",
                        imagen = R.drawable.tortagratis
                    )
                    repository.insertarCatalogo(regalo)
                }
            }
        }
    }

    fun guardarPastel(pastel: Catalogo) {
        viewModelScope.launch { repository.insertarCatalogo(pastel) }
    }

    fun cambiarEstadoEntregado() {
        _recordarEntrega.value = true
    }

    fun limpiarPedidos() {
        viewModelScope.launch { repository.eliminarCatalogo() }
    }
}