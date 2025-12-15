package com.example.app_pasteleria.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UsuarioConComentarios(
    @Embedded val usuario: Usuario, // Incluye todos los campos de Usuario

    @Relation(
        parentColumn = "id",        // Usuario.id
        entityColumn = "idUsuario"  // Comentario.idUsuario
    )
    val comentarios: List<Comentario>
)

