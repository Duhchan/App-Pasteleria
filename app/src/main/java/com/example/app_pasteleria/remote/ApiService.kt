package com.example.app_pasteleria.remote

import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.model.TortaApi
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("de4272f76157fea19852")
    suspend fun obtenerTortasDesdeInternet(): List<TortaApi>

    // OBTENER EL CARRITO (Lectura)
    @GET("1882fb60a0892b6ed6aa")
    suspend fun obtenerCarritoNube(): List<Catalogo>

    //GUARDAR EL CARRITO (Sobreescritura)

    @POST("1882fb60a0892b6ed6aa")
    suspend fun sobreescribirCarrito(@Body carritoCompleto: List<Catalogo>): List<Catalogo>
}