package com.example.app_pasteleria.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_pasteleria.data.model.Usuario

@Dao
interface UsuarioDao {
    // Registra un nuevo usuario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarUsuario(usuario: Usuario)

    // Busca un usuario por correo y contraseña (para el Login)
    @Query("SELECT * FROM usuarios WHERE correo = :correo AND password = :password LIMIT 1")
    suspend fun loginUsuario(correo: String, password: String): Usuario?

    // Verifica si el correo ya existe (opcional pero recomendado)
    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun buscarPorCorreo(correo: String): Usuario?
}