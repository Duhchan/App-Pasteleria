package com.example.app_pasteleria.view


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable

fun DrawerMenu(
    username: String,
    navController: NavController
){ //inicio

    Column(modifier = Modifier.fillMaxSize())
    { // inicio columna
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp) // dp: densidad de pixeles
                .background(MaterialTheme.colorScheme.primary)
        ) // fin box
        { // inicio contenido
            Text(
                text = "Categorias user:$username",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )
        } // fin contenido
        // LazyColumn: crear lista de elementos que se pueden desplazar verticalmente
        LazyColumn(modifier = Modifier.weight(1f)){
            item { // inicio item 1
                NavigationDrawerItem(
                    label = { Text("Hamburguesa Clásica")},
                    selected = false,
                    onClick = {
                        val nombre = Uri.encode("Hamburguesa Clásica")
                        val precio = "5000"
                        navController.navigate("CatalogoFormScreen/$nombre/$precio")
                    }, // fin OnClick
                    icon = { Icon(Icons.Default.Fastfood , contentDescription = "Clásica") }
                )
            } // fin item 1

            item { // inicio item 2
                NavigationDrawerItem(
                    label = { Text("Hamburguesa BBQ")},
                    selected = false,
                    onClick = {

                        /* accion futura*/

                    }, // fin OnClick
                    icon = { Icon(Icons.Default.LunchDining , contentDescription = "BBQ") }
                )
            } // fin item 2

            item { // inicio item 3
                NavigationDrawerItem(
                    label = { Text("Hamburguesa Veggie")},
                    selected = false,
                    onClick = {

                        /* accion futura*/

                    }, // fin OnClick
                    icon = { Icon(Icons.Default.Grass , contentDescription = "Veggie") }
                )
            } // fin item 3

            item { // inicio item 4
                NavigationDrawerItem(
                    label = { Text("Hamburguesa Picante")},
                    selected = false,
                    onClick = {

                        /* accion futura*/

                    }, // fin OnClick
                    icon = { Icon(Icons.Default.LocalFireDepartment , contentDescription = "Picante") }
                )
            } // fin item 4

            item { // inicio item 5
                NavigationDrawerItem(
                    label = { Text("Hamburguesa Doble")},
                    selected = false,
                    onClick = {

                        /* accion futura*/

                    }, // fin OnClick
                    icon = { Icon(Icons.Default.Star , contentDescription = "Doble") }
                )
            } // fin item 5

        } // fin LazyColumn

        // Footer
        Text(
            text = "@ 2025 Burger App",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

    } // fin columna
} // fin DrawerMenu


@Preview(showBackground = true)
@Composable
fun DrawerMenuPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    DrawerMenu(username = "Usuario Prueba", navController = navController)
}