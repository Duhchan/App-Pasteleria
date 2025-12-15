package com.example.app_pasteleria.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_pasteleria.data.model.Comentario
import kotlinx.coroutines.flow.Flow

@Dao
interface ComentarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComentario(comentario: Comentario)

    @Query("SELECT * FROM comentarios WHERE idUsuario = :usuarioId ")
    fun obtenerComentariosPorUsuario(usuarioId: Int): Flow<List<Comentario>>

    @Query("DELETE FROM comentarios")
    suspend fun eliminarTodosLosComentarios()
}