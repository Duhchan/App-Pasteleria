package com.example.app_pasteleria.data.repository



import com.example.app_pasteleria.data.dao.UsuarioDao
import com.example.app_pasteleria.data.model.Credential
import com.example.app_pasteleria.data.model.Usuario

class AuthRepository(private val usuarioDao: UsuarioDao) { // <--- Inyectamos el DAO

    //Busca en la BD o usa las credenciales de admin
    suspend fun login(correo: String, password: String): Boolean {

        if (correo == Credential.Admin.correo && password == Credential.Admin.password) return true

        val usuarioEncontrado = usuarioDao.loginUsuario(correo, password)
        return usuarioEncontrado != null
    }

    // Guarda en la BD
    suspend fun registro(correo: String, password: String): Boolean {
        if (correo.isBlank() || password.isBlank()) return false

        // Verificamos si ya existe
        val existe = usuarioDao.buscarPorCorreo(correo)
        if (existe != null) return false // Ya existe ese correo

        // Guardamos
        val nuevoUsuario = Usuario(correo = correo, password = password)
        usuarioDao.registrarUsuario(nuevoUsuario)
        return true
    }
}




