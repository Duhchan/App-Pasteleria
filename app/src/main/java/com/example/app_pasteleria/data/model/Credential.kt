package com.example.app_pasteleria.data.model


data class Credential(val username : String ,val password :String){
    //objeto que permite acceder a la instancia de la clase
    companion object{
        val Admin = Credential(username="admin", password = "123")
    }//fin companion

}//f