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
import com.example.app_pasteleria.ui.registro.RegistroViewModel
import com.example.app_pasteleria.view.DrawerMenu
import com.example.app_pasteleria.view.CatalogoFormScreen
import com.example.app_pasteleria.view.PostScreen
import com.example.app_pasteleria.view.QrScannerScreen
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import com.example.app_pasteleria.viewmodel.QrViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNav(viewModel: CatalogoViewModel){
    //Crear el controlador
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = "login")
    {
        composable("login"){
            LoginScreen(navController = navController)

        }
        composable("registro"){

            val registroViewModel: RegistroViewModel = viewModel()

            RegistroScreen(
                navController = navController,
                vm = registroViewModel
            )
        }//composable

        composable(
            route="DrawerMenu/{correo}",
            arguments = listOf(
                navArgument("correo"){
                    type = NavType.StringType
                }
            )//fin lisof
        )// fin composable
        {//inicio
                backStackEntry ->
            val obtenerCorreo = backStackEntry.arguments?.getString("correo") ?: ""
            val correo = Uri.decode(obtenerCorreo)
            DrawerMenu(correo = correo, navController= navController, viewModel = viewModel)
        }

        // ruta del Formulario: ProductoFormScreen

        composable(
            route="CatalogoFormScreen/{nombre}/{precio}/{descripcion}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("precio"){ type = NavType.StringType },
                navArgument("descripcion"){ type = NavType.StringType }
            )//fin lisof
        ) // fin composable

        { // inicio
                backStackEntry ->
            val nombre = Uri.decode(backStackEntry.arguments?.getString("nombre") ?:"")
            val precio = backStackEntry.arguments?.getString("precio") ?:""
            val descripcion = backStackEntry.arguments?.getString("descripcion") ?:""
            CatalogoFormScreen(
                navController = navController,
                nombre= nombre,
                precio= precio,
                descripcion = descripcion,
                viewModel = viewModel
            )

        }

        composable("QrScannerScreen") {

            val qrViewModel: QrViewModel = viewModel()

            // lógica de permisos
            val cameraPermissionState = rememberPermissionState(
                android.Manifest.permission.CAMERA
            )

            // Pide el permiso
            LaunchedEffect(Unit) {
                cameraPermissionState.launchPermissionRequest()
            }

            // pantalla QR
            QrScannerScreen(
                viewModel = qrViewModel,
                navController = navController,
                hasCameraPermission = cameraPermissionState.status.isGranted,
                onRequestPermission = {
                    cameraPermissionState.launchPermissionRequest()
                }
            )
        }
        composable("PostScreen") {
            PostScreen()
        }








    }//fin NavHost

}//fin appNav