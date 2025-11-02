package com.example.app_pasteleria.data.model


data class Credential(val correo : String ,val password :String){
    //objeto que permite acceder a la instancia de la clase
    companion object{
        val Admin = Credential(correo="admin@gmail.com", password = "123")
        val Usuario1 = Credential(correo = "usuario1@gmail.com", password = "123")
        val Usuario2 = Credential(correo = "usuario2@duocuc.cl", password ="123")

    }//fin companion

}//