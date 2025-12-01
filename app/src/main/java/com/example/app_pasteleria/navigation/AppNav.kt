package com.example.app_pasteleria.navigation

import RegistroScreen
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_pasteleria.ui.login.LoginScreen
import com.example.app_pasteleria.ui.login.LoginViewModel
import com.example.app_pasteleria.ui.registro.RegistroViewModel
import com.example.app_pasteleria.view.CatalogoFormScreen
import com.example.app_pasteleria.view.DrawerMenu
import com.example.app_pasteleria.view.QrScannerScreen
import com.example.app_pasteleria.viewmodel.AuthViewModelFactory // Importante
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import com.example.app_pasteleria.viewmodel.QrViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNav(
    catalogoViewModel: CatalogoViewModel, // Antes se llamaba solo viewModel
    authViewModelFactory: AuthViewModelFactory // <--- ESTO FALTABA
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // --- PANTALLA LOGIN ---
        composable("login") {
            // Aquí usamos la factory que recibimos arriba
            val loginViewModel: LoginViewModel = viewModel(factory = authViewModelFactory)

            LoginScreen(navController = navController, vm = loginViewModel)
        }

        // --- PANTALLA REGISTRO ---
        composable("registro") {
            // Aquí también usamos la factory
            val registroViewModel: RegistroViewModel = viewModel(factory = authViewModelFactory)

            RegistroScreen(
                navController = navController,
                vm = registroViewModel
            )
        }

        // --- MENU PRINCIPAL (Drawer) ---
        composable(
            route = "DrawerMenu/{correo}",
            arguments = listOf(navArgument("correo") { type = NavType.StringType })
        ) { backStackEntry ->
            val obtenerCorreo = backStackEntry.arguments?.getString("correo") ?: ""
            val correo = Uri.decode(obtenerCorreo)
            // Aquí pasamos el catalogoViewModel que ya venía de antes
            DrawerMenu(correo = correo, navController = navController, viewModel = catalogoViewModel)
        }

        // --- FORMULARIO PRODUCTO ---
        composable(
            route = "CatalogoFormScreen/{nombre}/{precio}/{descripcion}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("precio") { type = NavType.StringType },
                navArgument("descripcion") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = Uri.decode(backStackEntry.arguments?.getString("nombre") ?: "")
            val precio = backStackEntry.arguments?.getString("precio") ?: ""
            val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""

            CatalogoFormScreen(
                navController = navController,
                nombre = nombre,
                precio = precio,
                descripcion = descripcion,
                viewModel = catalogoViewModel
            )
        }

        // --- ESCANER QR ---
        composable("QrScannerScreen") {
            val qrViewModel: QrViewModel = viewModel() // Este no necesita factory por ahora

            val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

            LaunchedEffect(Unit) {
                cameraPermissionState.launchPermissionRequest()
            }

            QrScannerScreen(
                viewModel = qrViewModel,
                navController = navController,
                hasCameraPermission = cameraPermissionState.status.isGranted,
                onRequestPermission = { cameraPermissionState.launchPermissionRequest() }
            )
        }
    }
}