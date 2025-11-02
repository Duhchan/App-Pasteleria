package com.example.app_pasteleria.view

import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_pasteleria.R
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.viewmodel.CatalogoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CatalogoFormScreen(
    navController: NavController,
    nombre:String,
    precio:String,
    descripcion:String,
    imagen:Int=0,
    viewModel: CatalogoViewModel
){// Inicio

    fun obtenerImagenPastel(nombrePastel: String): Int {
        return when (nombrePastel) {
            "Torta de Chocolate" -> R.drawable.tortachocolate
            "Torta de Frutas" -> R.drawable.tortafruta
            "Torta de Vainilla" -> R.drawable.tortavainilla
            "Torta de Manjar" -> R.drawable.tortacircularmanjar
            "Mousse de Chocolate" -> R.drawable.postremoussechocolate
            "Tiramisú Clásico" -> R.drawable.postretiramisu
            "Torta de Naranja" -> R.drawable.tortanaranja
            "Cheesecake sin Azúcar" -> R.drawable.cheesecake
            "Empanada de Manzana" -> R.drawable.empanadamanzana
            "Pan sin Gluten" -> R.drawable.pansingluten
            "Tarta de Santiago" -> R.drawable.tartasantiago
            "Brownie sin Gluten" -> R.drawable.brownie
            "Torta Vegana de Chocolate" -> R.drawable.tortaceganachocolate
            "Galletas Veganas de Avena" -> R.drawable.galletaavena
            "Torta Especial de Cumpleaños" -> R.drawable.tortacumpleanios
            "Torta Especial de Boda" -> R.drawable.tortaboda
            else -> android.R.drawable.ic_menu_gallery
        }
    }

    var cantidad by remember{ mutableStateOf(TextFieldValue("")) }
    var direccion by remember{ mutableStateOf(TextFieldValue("")) }




    //Observar los datos en tiempo real

    val pasteles: List<Catalogo> by viewModel.pasteles.collectAsState()

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(title = {Text("Detalle de Producto",
                style= MaterialTheme.typography.headlineLarge,
                color= Color(0xFFFFF3E0),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.background(Color(0xFF79594F))

            )}, // fin Bootom App
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFF79594F)))
        }// fin bottom

    ) // fin Scaffold

    {// inicio inner
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color(0xFFFFDFBF)),
            horizontalAlignment = Alignment.CenterHorizontally
        )// fin Column
        { // Inicio Contenido

            Image(
                painter= painterResource(id= obtenerImagenPastel(nombre)),
                contentDescription = "Imagen Producto",
                modifier=Modifier
                    .padding(vertical = 16.dp)
                    .height(200.dp)
                    .fillMaxWidth()

            )// fin Image
            Spacer(modifier =Modifier.height(16.dp))

            Text(
                text = nombre,
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )


            Text(text="Precio: $precio",
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center)

            Spacer(modifier =Modifier.height(10.dp))
            Text(text = descripcion,
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold)
            Spacer(modifier =Modifier.height(16.dp))


            OutlinedTextField(
                value=cantidad,
                onValueChange = {
                    if (it.text.isEmpty() || it.text.all { char -> char.isDigit() }) {
                        cantidad = it
                    }
                },
                label ={Text("Cantidad")},
                modifier = Modifier.padding(20.dp).fillMaxWidth()
            ) // fin cantidad

            OutlinedTextField(
                value=direccion,
                onValueChange = {direccion = it},
                //OutlinedTextField es un componente de entrada de texto
                // se utiliza para permitir que el usuario ingrese un valor.

                label ={Text("Direccion")},
                modifier = Modifier.padding(20.dp).fillMaxWidth()
            ) // fin direccion

            Spacer(modifier =Modifier.height(16.dp))

            Button(
                onClick = {

                    val catalogo= Catalogo(
                        nombre= nombre,
                        precio= precio,
                        descripcion = descripcion,
                        imagen = imagen
                    )
                    //hace la magia
                    viewModel.guardarPastel(catalogo)

                    //limpiar datos
                    cantidad = TextFieldValue("")
                    direccion = TextFieldValue("")

                },
                enabled=cantidad.text.isNotBlank() && direccion.text.isNotBlank()
            ) // fin Button
            { // inicio texto
                Text("Confirmar Pedido")

            }// fin texto
            Spacer(modifier =Modifier.height(16.dp))

            //Mostrar los productos guardados

            Text ("Pedidos Realizados: ",
                color = Color(0xFF5D4037),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)

            if(pasteles.isNotEmpty()){
                LazyColumn(modifier= Modifier.weight(1f)){
                    items(pasteles){ catalogo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .border(
                                    width = 2.dp,
                                    Color(0xFF886655),
                                    shape = RoundedCornerShape(20.dp))
                                .background(Color(0xFFFFF3E0))

                        )
                        {//inicio del contenido

                            Text(
                                text="${catalogo.nombre} - ${catalogo.precio}",
                                style= MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF5D4037),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                            )//fin text 1

                        }//fin del contenido
                    }// fin items
                }// fin Lazy
            }//fin if
            else{
                Text("No hay pedidos realizados",
                    modifier = Modifier.weight(1f),
                    style= MaterialTheme.typography.bodyMedium
                )// fin text
            }//fin else
            // Footer

        } //Fin Contenido

    } // fin inner

}//fin

