package com.example.app_pasteleria.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.app_pasteleria.data.model.Catalogo
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogoDao{
    @Insert
    suspend fun insertarPastel(pastel: Catalogo)

    @Query("SELECT * FROM catalogo")
    fun obtenerCatalogo(): Flow<List<Catalogo>>

    @Query("DELETE FROM catalogo")
    suspend fun eliminarCatalogo()

}