package com.example.app_pasteleria.data.model

// Este modelo es para leer lo que viene de internet (JSON)
data class TortaApi(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val imagenNombre: String // Viene como texto
)