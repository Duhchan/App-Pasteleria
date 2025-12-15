package com.example.app_pasteleria.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comentarios",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class, // Indica que la clave foránea apunta a la entidad Usuario
            parentColumns = ["id"], // La columna de Usuario a la que apunta
            childColumns = ["idUsuario"], // La columna en esta entidad que guarda la referencia
            onDelete = ForeignKey.CASCADE // Si se borra el Usuario, se borran sus Comentarios
        )
    ]
)
data class Comentario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val texto: String,
    //Clave Foránea: Guarda el ID del Usuario que creó el comentario.
    @ColumnInfo(name = "idUsuario")
    val idUsuario: Int
)