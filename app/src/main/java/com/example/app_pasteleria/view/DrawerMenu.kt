package com.example.app_pasteleria.view

import com.example.app_pasteleria.R
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.BrunchDining
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.viewmodel.CatalogoViewModel
import kotlinx.coroutines.launch

@Composable

fun DrawerMenu(
    correo: String,
    navController: NavController,
    viewModel: CatalogoViewModel
){ //inicio

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var mostrarAlertaDuoc by remember { mutableStateOf(false) }
    val regaloYaEntregado by viewModel.recordarEntrega.collectAsState()

    LaunchedEffect(Unit) {
        if (correo.trim().endsWith("@duocuc.cl") && !regaloYaEntregado) {
            val tortaRegalo= Catalogo(
                nombre = "Torta Especial de Cumpleaños",
                precio = "$0 (Regalo Duoc)",
                descripcion = "Torta de regalo por ser estudiante de Duoc UC",
                imagen = R.drawable.tortacumpleanios
            )
            viewModel.guardarPastel(tortaRegalo)
            viewModel.cambiarEstadoEntregado()
            mostrarAlertaDuoc = true
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(Color(0xFF79594F))
            ) {
                Text(
                    text = "Catalogo Pasteles",
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
                containerColor = Color(0xFFFFDFBF) // El color de fondo de tu footer
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

    )
    { innerPadding ->


            // LazyColumn: crear lista de elementos que se pueden desplazar verticalmente

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFFFDFBF))
            ) {
                item { //torta chocolate
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta de Chocolate",
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
                            val nombre = Uri.encode("Torta de Chocolate")
                            val precio = "$45000"
                            val descripcion =
                                Uri.encode("Torta de Chocolate con relleno de chocolate")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")
                            scope.launch { drawerState.close() }
                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "Icono Torta",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        }
                    )
                } // fin item 1

                item { // torta de frutas
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta de Frutas",
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
                            val nombre = Uri.encode("Torta de Frutas")
                            val precio = "$50.000"
                            val descripcion = Uri.encode("Torta de Frutas con relleno de frutas")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "Icono Torta",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                } // fin item 2

                item { // Torta de Vainilla
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta de Vainilla",
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
                            val nombre = Uri.encode("Torta de Vainilla")
                            val precio = "$40.000"
                            val descripcion =
                                Uri.encode("Torta de Vainilla con relleno de vainilla")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "Icono Torta",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                } // fin item 3

                item { // Torta de Manjar
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta de Manjar",
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

                            val nombre = Uri.encode("Torta de Manjar")
                            val precio = "$42.000"
                            val descripcion = Uri.encode("Torta de Manjar con relleno de manjar")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "Icono Torta",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                } // fin item 4

                item { // Mousse de chocolata
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Mousse de Chocolate",
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
                            val nombre = Uri.encode("Mousse de Chocolate")
                            val precio = "$5.000"
                            val descripcion =
                                Uri.encode("Mousse de Chocolate con relleno de chocolate")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.BakeryDining, contentDescription = "Icono Torta",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                } // fin item 5
                item { // Tiramisú Clásico
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Tiramisú Clásico",
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
                            val nombre = Uri.encode("Tiramisú Clásico")
                            val precio = "$5.500"
                            val descripcion = Uri.encode("Tiramisú Clásico con relleno de crema")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.BakeryDining, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Torta de Naranja
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta de Naranja",
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
                            val nombre = Uri.encode("Torta de Naranja")
                            val precio = "$48.000"
                            val descripcion = Uri.encode("Torta de Naranja con relleno de naranja")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Cheesecake sin Azúcar
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Cheesecake sin Azúcar",
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
                            val nombre = Uri.encode("Cheesecake sin Azúcar")
                            val precio = "$47.000"
                            val descripcion =
                                Uri.encode("Cheesecake sin Azúcar con relleno de crema")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.BrunchDining, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { //Empanada de Manzana
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Empanada de Manzana",
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
                            val nombre = Uri.encode("Empanada de Manzana")
                            val precio = "$3.000"
                            val descripcion =
                                Uri.encode("Empanada de Manzana con relleno de manzana")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.BakeryDining, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Pan sin Gluten
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Pan sin Gluten",
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
                            val nombre = Uri.encode("Pan sin Gluten")
                            val precio = "$3.500"
                            val descripcion =
                                Uri.encode("Pan sin Gluten, una opción más sana y saludable")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.BrunchDining, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Tarta de Santiago
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Tarta de Santiago",
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
                            val nombre = Uri.encode("Tarta de Santiago")
                            val precio = "$6.000"
                            val descripcion =
                                Uri.encode("Tarta de Santiago con relleno de chocolate")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Brownie sin Gluten
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Brownie sin Gluten",
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
                            val nombre = Uri.encode("Brownie sin Gluten")
                            val precio = "$4.000"
                            val descripcion = Uri.encode("Brownie sin Gluten sin relleno")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.BakeryDining, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Torta Vegana de Chocolate
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta Vegana de Chocolate",
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
                            val nombre = Uri.encode("Torta Vegana de Chocolate")
                            val precio = "$50.000"
                            val descripcion =
                                Uri.encode("Torta Vegana de Chocolate con relleno de chocolate")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Galletas Veganas de Avena
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Galletas Veganas de Avena",
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
                            val nombre = Uri.encode("Galletas Veganas de Avena")
                            val precio = "$4.500"
                            val descripcion = Uri.encode("Galletas Veganas de Avena sin relleno")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cookie, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Torta Especial de Cumpleaños
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta Especial de Cumpleaños",
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
                            val nombre = Uri.encode("Torta Especial de Cumpleaños")
                            val precio = "$55.000"
                            val descripcion =
                                Uri.encode("Torta Especial de Cumpleaños con relleno de chocolate")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
                item { // Torta Especial de Boda
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Torta Especial de Boda",
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
                            val nombre = Uri.encode("Torta Especial de Boda")
                            val precio = "$60.000"
                            val descripcion =
                                Uri.encode("Torta Especial de Boda con relleno variado")
                            navController.navigate("CatalogoFormScreen/$nombre/$precio/$descripcion")

                        }, // fin OnClick
                        icon = {
                            Icon(
                                Icons.Filled.Cake, contentDescription = "icono",
                                tint = Color(0xFF5D4037),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }

            }
        if (mostrarAlertaDuoc) {
            AlertDialog(
                onDismissRequest = { mostrarAlertaDuoc = false },
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
                        text = "Por ser estudiante de duoc, tendrás una torta de cumpleaños Gratis.",
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
                            onClick = { mostrarAlertaDuoc = false }
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


} // fin DrawerMenu


