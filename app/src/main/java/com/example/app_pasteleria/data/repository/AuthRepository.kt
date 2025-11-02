package com.example.app_pasteleria.data.repository



import com.example.app_pasteleria.data.model.Credential

class AuthRepository (
    private val validCredential: Credential = Credential.Admin

){
    fun login (correo:String,password:String): Boolean {
        //validad las credenciales que traemos desde el modelo
        return correo == validCredential.correo && password == validCredential.password
    }

    fun Registro (correo: String, password: String): Boolean {
        if (correo.isBlank() || password.isBlank()) {
            return false
        }
        return true

    }






}//fin del fun




