package com.example.app_pasteleria.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.viewmodel.CatalogoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CatalogoFormScreen(
    navController: NavController,
    nombre:String,
    precio:String
){// Inicio

    var cantidad by remember{ mutableStateOf(TextFieldValue("")) }
    var direccion by remember{ mutableStateOf(TextFieldValue("")) }

    var conPapas  by remember{ mutableStateOf(false) }
    var agrandarBebida  by remember{ mutableStateOf(false) }

    //conexion a viewmodel
    val viewModel: CatalogoViewModel = viewModel ()

    //Observar los datos en tiempo real

  val pasteles: List<Catalogo> by viewModel.catalogo.collectAsState()

    Scaffold (
        bottomBar = {
            BottomAppBar {
                // Contenido Barra superior
            } // fin Bootom App
        }// fin bottom

    ) // fin Scaffold

    {// inicio inner
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )// fin Column
        { // Inicio Contenido

            Image(
                painter= painterResource(id= android.R.drawable.ic_menu_gallery),
                contentDescription = "Imagen Producto",
                modifier=Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )// fin Image

            Spacer(modifier =Modifier.height(16.dp))

            Text(text=nombre, style= MaterialTheme.typography.headlineSmall)
            Text(text="Precio: $precio", style= MaterialTheme.typography.bodyLarge)

            Spacer(modifier =Modifier.height(16.dp))


            OutlinedTextField(
                value=cantidad,
                onValueChange = {cantidad = it},
                //OutlinedTextField es un componente de entrada de texto
                // se utiliza para permitir que el usuario ingrese un valor.

                label ={Text("Cantidad")},
                modifier = Modifier.fillMaxWidth()
            ) // fin cantidad

            OutlinedTextField(
                value=direccion,
                onValueChange = {direccion = it},
                //OutlinedTextField es un componente de entrada de texto
                // se utiliza para permitir que el usuario ingrese un valor.

                label ={Text("Direccion")},
                modifier = Modifier.fillMaxWidth()
            ) // fin direccion

            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked =conPapas,
                    onCheckedChange = {conPapas = it}
                )
                Text("Agrandar Papas Fritas")
            }// fin row 1

            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked =agrandarBebida,
                    onCheckedChange = {agrandarBebida = it}
                )
                Text("Agrandar Bebida")
            }// fin row 2

            Spacer(modifier =Modifier.height(16.dp))

            Button(
                onClick = {

                    val catalogo= Catalogo(
                        nombre= nombre,
                        precio= precio,
                        cantidad = cantidad.text,
                        direccion= direccion.text,
                        conPapas = conPapas,
                        agrandarBebida=agrandarBebida
                    )
                    //hace la magia
                    viewModel.guardarPastel(catalogo)

                    //limpiar datos
                    cantidad = TextFieldValue("")
                    direccion = TextFieldValue("")
                    conPapas = false
                    agrandarBebida = false


                },
                enabled=cantidad.text.isNotBlank() && direccion.text.isNotBlank()
            ) // fin Button
            { // inicio texto
                Text("Confirmar Pedido")

            }// fin texto
            Spacer(modifier =Modifier.height(16.dp))

            //Mostrar los productos guardados

            Text ("Pedidos Realizados: ", style = MaterialTheme.typography.headlineSmall)
            if(pasteles.isNotEmpty()){
                LazyColumn(modifier= Modifier.weight(1f)){
                    items(pasteles){ catalogo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                        {//inicio del contenido

                            Text(
                                text="${catalogo.nombre} - ${catalogo.precio}",
                                style= MaterialTheme.typography.bodyLarge
                            )//fin text 1
                            Text(
                                text="Cantidad: ${catalogo.cantidad}",
                                style = MaterialTheme.typography.bodyMedium
                            )//fin text 2
                            Text(
                                text="Direccion: ${catalogo.direccion}",
                                style= MaterialTheme.typography.bodyMedium
                            )//fin text 3

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

        } //Fin Contenido

    } // fin inner

}//fin


@Preview(showBackground = true)
@Composable
fun PreviewCatalogoFormScreen() {
    // Preview básico para testing
    CatalogoFormScreen(
        navController = rememberNavController(),
        nombre = "Producto Ejemplo",
        precio = "$10.00"
    )
}