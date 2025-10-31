package com.example.app_pasteleria.data.repository



import com.example.app_pasteleria.data.model.Credential

class AuthRepository (
    private val validCredential: Credential = Credential.Admin
){
    fun login (username:String,password:String): Boolean {
        //validad las credenciales que traemos desde el modelo
        return username == validCredential.username && password == validCredential.password
    }

}//fin del fun




