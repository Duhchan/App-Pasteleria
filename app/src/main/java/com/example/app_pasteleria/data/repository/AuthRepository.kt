package com.example.app_pasteleria.data.repository



import com.example.app_pasteleria.data.model.Credential

class AuthRepository (
<<<<<<< HEAD
    private val validCredential: Credential = Credential.Admin

=======
    private val validCredential: Credential = Credential.Admin,
    private val validCredential2: Credential = Credential.Usuario1,
    private val validCredential3: Credential = Credential.Usuario2
>>>>>>> 26de5656bdf7541c96f01dd1da90ca932097fd98
){
    fun login (correo:String,password:String): Boolean {
        //validad las credenciales que traemos desde el modelo
        if (correo == validCredential.correo && password == validCredential.password) return true
        if (correo.endsWith("@duocuc.cl") && password == validCredential2.password) return true
        if (correo == validCredential3.correo && password == validCredential3.password) return true
        return false
    }

    fun Registro (correo: String, password: String): Boolean {
        if (correo.isBlank() || password.isBlank()) {
            return false
        }
        return true

    }






}//fin del fun




