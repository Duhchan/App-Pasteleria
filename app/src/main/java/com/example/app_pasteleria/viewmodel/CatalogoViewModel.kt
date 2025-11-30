package com.example.app_pasteleria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    //ESTADO DEL CARRITO
    private val _pedidos = MutableStateFlow<List<Catalogo>>(emptyList())
    val pedidos: StateFlow<List<Catalogo>> = _pedidos.asStateFlow()

    //ESTADO DEL MENÚ (Tortas para elegir)
    private val _menuTortas = MutableStateFlow<List<Catalogo>>(emptyList())
    val menuTortas: StateFlow<List<Catalogo>> = _menuTortas.asStateFlow()


    var saludoYaMostrado: Boolean = false


    var comentario by mutableStateOf("")
        private set

    init {
        // Al iniciar, cargamos el menú y el carrito desde internet
        cargarDatosIniciales()
    }

    private fun cargarDatosIniciales() {
        viewModelScope.launch {
            // A) Cargar menú de tortas
            _menuTortas.value = repository.obtenerTortasDeInternet()

            // B) Cargar carrito actual de la nube
            recargarCarrito()
        }
    }

    // Función para refrescar el carrito desde npoint
    fun recargarCarrito() {
        viewModelScope.launch {
            val carritoNube = repository.obtenerCarritoNube()
            _pedidos.value = carritoNube
        }
    }

    fun guardarPastel(pastel: Catalogo) {
        viewModelScope.launch {
            // 1. Subimos el cambio
            repository.agregarAlCarritoNube(pastel)
            // 2. Recargamos la lista para ver el cambio reflejado
            recargarCarrito()
        }
    }

    fun eliminarPastel(pastel: Catalogo) {
        viewModelScope.launch {
            repository.eliminarDelCarritoNube(pastel)
            recargarCarrito()
        }
    }

    fun actualizarCantidad(pastel: Catalogo, nuevaCantidad: Int) {
        viewModelScope.launch {
            repository.actualizarCantidadNube(pastel, nuevaCantidad)
            recargarCarrito()
        }
    }

    // Función para el campo de texto de comentario
    fun actualizarComentario(nuevoTexto: String) {
        comentario = nuevoTexto
    }

    // Lógica del regalo
    fun validarRegalo(correo: String) {
        val esCorreoDuoc = correo.lowercase().contains("duocuc.cl") ||
                correo.lowercase().contains("profesor@duoc.cl")

        if (esCorreoDuoc && !saludoYaMostrado) {
            // Aquí podrías agregar lógica extra si quisieras guardar el regalo en la nube
        }
    }
}