package com.example.app_pasteleria.ui.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_pasteleria.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: AuthRepository): ViewModel(){
    var uiState by mutableStateOf(LoginUiState())
    fun onCorreoChange(value:String){
        uiState = uiState.copy(correo =value, error= null )
        //la funcion copy es la que lleva el contenido hacia atras
    }//fin nameChange

    fun onPasswordChange(value:String){
        uiState = uiState.copy(password =value, error= null )
    }//fin de passwordChange
    //funciones de la actualizacion que se llaman desde textFiel de la ui


    fun submit(onSucces:(String)->Unit){
        uiState = uiState.copy(isLoading = true, error = null)

        viewModelScope.launch { // <--- Lanzamos corrutina
            val oK = repo.login(uiState.correo.trim(), uiState.password)

            uiState = uiState.copy(isLoading = false, error= null)

            if (oK) onSucces(uiState.correo.trim())
            else uiState = uiState.copy(error = "Credenciales Inválidas")
        }
    }

}//  fin viewModel