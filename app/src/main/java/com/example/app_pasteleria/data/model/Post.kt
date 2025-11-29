package com.example.app_pasteleria.data.model


// Cada "noticia" o "post" que viene de internet

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)