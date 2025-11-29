package com.example.app_pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.data.model.Post
import com.example.app_pasteleria.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    // Estado de la lista de posts
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        // Cargar datos apenas se inicie
        cargarPosts()
    }

    fun cargarPosts() {
        viewModelScope.launch {
            try {
                val resultado = repository.obtenerPosts()
                _posts.value = resultado
            } catch (e: Exception) {
                // Aquí podrías manejar errores
                e.printStackTrace()
            }
        }
    }
}