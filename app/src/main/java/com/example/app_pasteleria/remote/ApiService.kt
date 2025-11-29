package com.example.app_pasteleria.remote

import com.example.app_pasteleria.data.model.Post
import com.example.app_pasteleria.data.model.TortaApi
import retrofit2.http.GET

interface ApiService {
    // ⚠️ REEMPLAZA "TU_CODIGO_AQUI" CON EL CÓDIGO DE NPOINT DEL PASO 1
    // Ejemplo: @GET("abc12345")
    @GET("de4272f76157fea19852")
    suspend fun obtenerTortasDesdeInternet(): List<TortaApi>
    fun getPosts(): List<Post>
}