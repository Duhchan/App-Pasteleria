package com.example.app_pasteleria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalogo")
data class Catalogo(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    val nombre : String,
    val precio :String,
    val cantidad : String,
    val direccion : String,
    val conPapas : Boolean,
    val agrandarBebida : Boolean
)