package com.example.app_pasteleria.data.repository

import com.example.app_pasteleria.data.model.Post
import com.example.app_pasteleria.remote.RetrofitInstance

class PostRepository {
    // Función que llama a la API GET List<Post>
    suspend fun obtenerPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
}