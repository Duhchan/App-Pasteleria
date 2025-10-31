package com.example.app_pasteleria.navigation


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_pasteleria.ui.home.MuestraDatosScreen
import com.example.app_pasteleria.ui.login.LoginScreen
import com.example.app_pasteleria.view.DrawerMenu
import com.example.app_pasteleria.view.CatalogoFormScreen

@Composable
fun AppNav(){
    //Crear el controlador
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = "login")
    {
        composable("login"){
            LoginScreen(navController = navController)

        }    //composable

        composable(
            route="DrawerMenu/{username}",
            arguments = listOf(
                navArgument("username"){
                    type = NavType.StringType
                }
            )//fin lisof
        )// fin composable
        {//inicio
                backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            DrawerMenu(username= username, navController= navController)
        }

        // ruta del Formulario: ProductoFormScreen

        composable(
            route="ProductoFormScreen/{nombre}/{precio}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("precio"){ type = NavType.StringType }
            )//fin lisof
        ) // fin composable

        { // inicio
                backStackEntry ->
            val nombre = Uri.decode(backStackEntry.arguments?.getString("nombre") ?:"")
            val precio = backStackEntry.arguments?.getString("precio") ?:""

            CatalogoFormScreen(navController = navController, nombre= nombre, precio= precio)

        }

    }//fin NavHost

}//fin appNav