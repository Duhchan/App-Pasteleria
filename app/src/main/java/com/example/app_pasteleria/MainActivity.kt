package com.example.app_pasteleria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_pasteleria.data.database.CatalogoDataBase
import com.example.app_pasteleria.data.repository.CatalogoRepository
import com.example.app_pasteleria.navigation.AppNav
import com.example.app_pasteleria.ui.theme.AppPasteleriaTheme
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import com.example.app_pasteleria.viewmodel.CatalogoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = CatalogoDataBase.getDatabase(applicationContext)
        val repository = CatalogoRepository(database.catalogoDao())
        val factory = CatalogoViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[CatalogoViewModel::class.java]

        setContent {
            AppNav(viewModel = viewModel)
        }
    }
}

