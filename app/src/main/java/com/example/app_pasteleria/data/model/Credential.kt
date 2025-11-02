package com.example.app_pasteleria.data.model


data class Credential(val correo : String ,val password :String){
    //objeto que permite acceder a la instancia de la clase
    companion object{
        val Admin = Credential(correo="admin@gmail.com", password = "123")
    }//fin companion

}//f