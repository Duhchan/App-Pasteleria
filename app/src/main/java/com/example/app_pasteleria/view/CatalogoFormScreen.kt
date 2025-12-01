package com.example.app_pasteleria.view

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn // Se puede borrar si no se usa, pero no molesta
import androidx.compose.foundation.lazy.items // Se puede borrar
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.QrCodeScanner
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_pasteleria.R
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.viewmodel.CatalogoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoFormScreen(
    navController: NavController,
    nombre: String,
    precio: String,
    descripcion: String,
    imagen: Int = 0,
    viewModel: CatalogoViewModel
) {
    // --- FUNCIÓN CORREGIDA PARA OBTENER TODAS LAS IMÁGENES ---
    fun obtenerImagenPastel(nombrePastel: String): Int {
        // Normalizamos el texto (minúsculas y sin espacios) para facilitar la coincidencia
        val nombreLimpio = nombrePastel.lowercase().replace(" ", "").replace("de", "")

        return when {
            // Coincidencias exactas o aproximadas con tus drawables
            nombreLimpio.contains("chocolate") && nombreLimpio.contains("torta") -> R.drawable.tortachocolate
            nombreLimpio.contains("fruta") -> R.drawable.tortafruta
            nombreLimpio.contains("vainilla") -> R.drawable.tortavainilla
            nombreLimpio.contains("manjar") -> R.drawable.tortacircularmanjar
            nombreLimpio.contains("mousse") -> R.drawable.postremoussechocolate
            nombreLimpio.contains("tiramisu") -> R.drawable.postretiramisu
            nombreLimpio.contains("naranja") -> R.drawable.tortanaranja
            nombreLimpio.contains("cheesecake") -> R.drawable.cheesecake
            nombreLimpio.contains("manzana") -> R.drawable.empanadamanzana
            nombreLimpio.contains("gluten") -> R.drawable.pansingluten
            nombreLimpio.contains("santiago") -> R.drawable.tartasantiago
            nombreLimpio.contains("brownie") -> R.drawable.brownie
            nombreLimpio.contains("vegana") -> R.drawable.tortaceganachocolate
            nombreLimpio.contains("avena") -> R.drawable.galletaavena
            nombreLimpio.contains("cumple") -> R.drawable.tortacumpleanios
            nombreLimpio.contains("boda") -> R.drawable.tortaboda

            // Si ya venía una imagen válida (ID distinto de 0), úsala
            imagen != 0 -> imagen

            // Si no, muestra el logo por defecto
            else -> R.drawable.logo
        }
    }

    // Estados del formulario
    var cantidad by remember { mutableStateOf(TextFieldValue("")) }
    var promocion by remember { mutableStateOf(TextFieldValue("")) }
    var mostrarVentanaPromo by remember { mutableStateOf(false) }
    var comentarioInput by remember { mutableStateOf(TextFieldValue("")) }

    // Estado para mostrar/ocultar el carrito desplegable
    var mostrarCarrito by remember { mutableStateOf(false) }

    // Obtenemos la lista de pedidos de la NUBE
    val pastelesEnCarrito by viewModel.pedidos.collectAsState()

    // Resultado QrScannerScreen
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    LaunchedEffect(savedStateHandle) {
        val qrResult = savedStateHandle?.get<String>("qr_result")
        if (qrResult != null) {
            promocion = TextFieldValue(qrResult)
            savedStateHandle.remove<String>("qr_result")
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Detalle de Producto",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFFFFF3E0),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF79594F)
                ),
                actions = {
                    Box {
                        IconButton(onClick = { mostrarCarrito = true }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Carrito",
                                tint = Color(0xFFFFF3E0),
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        // Llamamos al componente del carrito
                        CarritoDesplegable(
                            expanded = mostrarCarrito,
                            onDismissRequest = { mostrarCarrito = false },
                            pedidos = pastelesEnCarrito,
                            viewModel = viewModel
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color(0xFFFFDFBF)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- IMAGEN Y DETALLES DEL PRODUCTO ---
            // Aquí usamos la función corregida
            Image(
                painter = painterResource(id = obtenerImagenPastel(nombre)),
                contentDescription = "Imagen Producto",
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Text(
                text = nombre,
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Precio: $$precio",
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = descripcion,
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // --- FORMULARIO ---
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = cantidad,
                onValueChange = {
                    if (it.text.isEmpty() || it.text.all { char -> char.isDigit() }) {
                        cantidad = it
                    }
                },
                label = { Text("Cantidad") },
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
            )

            OutlinedTextField(
                value = promocion,
                onValueChange = { promocion = it },
                label = { Text("Codigo de Promocion") },
                trailingIcon = {
                    IconButton(onClick = { navController.navigate("QrScannerScreen") }) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = "Escanear QR")
                    }
                },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp).fillMaxWidth()
            )
            OutlinedTextField(
                value = comentarioInput,
                onValueChange = { comentarioInput = it },
                label = { Text("Comentario (Opcional)") },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp).fillMaxWidth(),
                maxLines = 3
            )

            // Botón Agregar
            Button(
                onClick = {
                    val cantidadNum = cantidad.text.toIntOrNull() ?: 1
                    // Limpiamos el precio ($ y puntos) para guardarlo como Int
                    val precioLimpio = precio.replace("$", "").replace(".", "").replace(" ", "").toIntOrNull() ?: 0

                    var precioUnitario = precioLimpio

                    if (promocion.text.trim().equals("FELICES50", ignoreCase = true)) {
                        mostrarVentanaPromo = true
                        precioUnitario = precioLimpio / 2
                    } else {
                        mostrarVentanaPromo = false
                    }

                    val catalogo = Catalogo(
                        nombre = nombre,
                        precio = precioUnitario,
                        descripcion = descripcion,
                        // Guardamos la imagen correcta también en el carrito
                        imagen = obtenerImagenPastel(nombre),
                        cantidad = cantidadNum,
                        comentario = comentarioInput.text

                    )

                    viewModel.guardarPastel(catalogo)

                    cantidad = TextFieldValue("")
                    promocion = TextFieldValue("")
                },
                enabled = cantidad.text.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF79594F),
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text("Agregar al Carrito")
            }
        }

        if (mostrarVentanaPromo) {
            AlertDialog(
                onDismissRequest = { mostrarVentanaPromo = false },
                containerColor = Color(0xFFFFF3E0),
                shape = RoundedCornerShape(20.dp),
                title = { Text("¡Promoción Activada!", fontWeight = FontWeight.Bold, color = Color(0xFF5D4037)) },
                text = { Text("50% de descuento aplicado.", color = Color(0xFF5D4037)) },
                confirmButton = {
                    TextButton(onClick = { mostrarVentanaPromo = false }) { Text("OK") }
                }
            )
        }
    }
}

// --- COMPONENTE DEL CARRITO DESPLEGABLE ---
@Composable
fun CarritoDesplegable(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    pedidos: List<Catalogo>,
    viewModel: CatalogoViewModel
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(Color(0xFFFFDFBF))
            .border(2.dp, Color(0xFF886655), RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Mi Carrito",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.Cursive,
                color = Color(0xFF5D4037),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            if (pedidos.isEmpty()) {
                Text("El carrito está vacío", color = Color(0xFF5D4037))
            } else {
                Column(
                    modifier = Modifier
                        .heightIn(max = 400.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    pedidos.forEach { pastel ->
                        ItemCarrito(pastel, viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                val total = pedidos.sumOf { it.precio * it.cantidad }
                Text(
                    "Total: $$total",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF5D4037)
                )
            }
        }
    }
}

@Composable
fun ItemCarrito(pastel: Catalogo, viewModel: CatalogoViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, Color(0xFF886655), RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(pastel.nombre, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                Text("$${pastel.precio}", fontSize = 12.sp, color = Color(0xFF5D4037))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { viewModel.actualizarCantidad(pastel, pastel.cantidad - 1) }) {
                    Text("-", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF79594F))
                }
                Text("${pastel.cantidad}", fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
                IconButton(onClick = { viewModel.actualizarCantidad(pastel, pastel.cantidad + 1) }) {
                    Text("+", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF79594F))
                }
            }
            IconButton(onClick = { viewModel.eliminarPastel(pastel) }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
            }
        }
    }
}