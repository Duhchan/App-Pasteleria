package com.example.app_pasteleria.remote

import com.example.app_pasteleria.data.model.TortaApi
import retrofit2.http.GET

interface ApiService {

    @GET("de4272f76157fea19852")
    suspend fun obtenerTortasDesdeInternet(): List<TortaApi>

}