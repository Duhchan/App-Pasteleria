package com.example.app_pasteleria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.app_pasteleria.data.database.CatalogoDataBase
import com.example.app_pasteleria.data.repository.AuthRepository
import com.example.app_pasteleria.data.repository.CatalogoRepository
import com.example.app_pasteleria.navigation.AppNav
import com.example.app_pasteleria.viewmodel.AuthViewModelFactory
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import com.example.app_pasteleria.viewmodel.CatalogoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancias de Base de Datos
        val database = CatalogoDataBase.getDatabase(applicationContext)

        // Repositorios
        val catalogoRepository = CatalogoRepository(database.catalogoDao())
        val authRepository = AuthRepository(database.usuarioDao()) // Repo de usuarios

        // Factories
        val catalogoFactory = CatalogoViewModelFactory(catalogoRepository)
        val authFactory = AuthViewModelFactory(authRepository) // Factory de Auth

        //ViewModel Principal
        val catalogoViewModel = ViewModelProvider(this, catalogoFactory)[CatalogoViewModel::class.java]

        setContent {
            // Pasar AMBAS cosas a la navegación
            AppNav(
                catalogoViewModel = catalogoViewModel,
                authViewModelFactory = authFactory
            )
        }
    }
}