package com.example.app_pasteleria.view

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(
    correo: String,
    navController: NavController,
    viewModel: CatalogoViewModel
) {
    LaunchedEffect(key1 = correo) {
        viewModel.validarRegalo(correo)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 2. OBSERVAMOS LA LISTA QUE VIENE DE LA API (viewModel.menuTortas)
    val listaTortas by viewModel.menuTortas.collectAsState()


    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(Color(0xFF79594F))
            ) {
                Text(
                    text = "Catálogo Pasteles",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 40.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFFFFF3E0),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(10.dp)
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFFFDFBF)
            ) {
                Text(
                    text = "@ 2025 Pastelería Mil Sabores.",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF5D4037),
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFDFBF))
        ) {

            // 3. GENERAMOS LA LISTA DE TORTAS DESDE LA API
            items(listaTortas) { torta ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = torta.nombre,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5D4037)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            Color(0xFF886655),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Color(0xFFFFF3E0)),
                    selected = false,
                    onClick = {
                        // Navegamos pasando los datos
                        val nombre = Uri.encode(torta.nombre)
                        val precio = torta.precio
                        val descripcion = Uri.encode(torta.descripcion)

                        navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")
                        scope.launch { drawerState.close() }
                    },
                    icon = {
                        // Usamos la imagen convertida (Int)
                        Icon(
                            painter = painterResource(id = torta.imagen),
                            contentDescription = "Icono Torta",
                            tint = Color.Unspecified, // Mantiene colores originales
                            modifier = Modifier.size(40.dp)
                        )
                    }
                )
            }
        } // Fin LazyColumn


        // POPUP DE BIENVENIDA DUOC (Controlado por ViewModel)
        if (viewModel.mostrarAlertaRegalo) {
            AlertDialog(
                onDismissRequest = { viewModel.cerrarAlertaRegalo() },
                containerColor = Color(0xFFFFF3E0),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.border(
                    width = 2.dp,
                    color = Color(0xFF886655),
                    shape = RoundedCornerShape(20.dp)
                ),
                title = {
                    Text(
                        text = "¡Bienvenido, Estudiante!",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFF5D4037),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                text = {
                    Text(
                        text = "Por ser estudiante de Duoc, tienes una torta de regalo en tu lista de pedidos.",
                        fontFamily = FontFamily.Serif,
                        fontSize = 18.sp,
                        color = Color(0xFF5D4037),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = { viewModel.cerrarAlertaRegalo() }
                        ) {
                            Text("¡Entendido!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF79594F)
                            )
                        }
                    }
                }
            )
        }
    }
}