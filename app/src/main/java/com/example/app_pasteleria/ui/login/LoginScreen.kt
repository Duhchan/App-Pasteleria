package com.example.app_pasteleria.ui.login

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.app_pasteleria.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.time.format.TextStyle



@OptIn(ExperimentalMaterial3Api::class)
// Permite usar funciones Material 3 qe son experimentales
@Composable  // Genera Interfz Garfica

fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel= viewModel()

){
    val state = vm.uiState
    var showPass by remember { mutableStateOf(false) }//para implementar el mostrar contraseña

    // darkColorScheme  es una funcion de material3 que define un color oscuro
    val ColorScheme = darkColorScheme(
        primary= Color(0xFF79594F),
        onPrimary = Color.White,
        onSurface = Color(0xFF553A2A), //Gris
    ) // fin dark


    MaterialTheme(
        colorScheme = ColorScheme
    ){ // inicio Aplicar Material



        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(title = {Text("¡Pastelería 1000 Sabores!",
                    style= MaterialTheme.typography.headlineLarge,
                    color= Color(0xFF886655),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )})  // Encabezado Nombre de Pastelería
            }// Crea Estuctra basica de la pantalla Se define topBar, BottomBar

        ) // fin Scaff
        {// Inicio Inner
                innerPadding ->
            // Representa el espacio interno para que no choque con el topBar

            Column (  //   Colaca los elementos de la Ui
                modifier = Modifier
                    .padding( innerPadding)
                    // Evita que quede oculto
                    .fillMaxSize() // Hace que la columnna tome el todo el tamaño
                    .padding(16.dp)
                    .background(Color(0xFFFFDFBF)), // gris Claro
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally  // Centra horizontalmente
            //Define  que elementos dentro la columna estaran separados por 20.dp
            )// fin column
            {// inicio Contenido
                Text(text="¡Ingresa tus credenciales!",
                    style= MaterialTheme.typography.headlineLarge,
                    color= Color(0xFF886655),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                ) // Muestra un texto simple en la pantalla




                Image(  // insertar una imagen en la interfaz
                    painter= painterResource(id = R.drawable.logo),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                    // Ajusta la imagen para que encaje dentro del espacio

                ) // Fin Image
                if (state.error != null){
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = state.error?:"",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

// agregar un espacio entre la imagen y el boton

                Spacer(modifier = Modifier.height(66.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 0.dp)
                )// Fin Row
                {// Aplica row
                    Text("Correo Electrónico",
                        style =MaterialTheme.typography.bodyLarge.copy(
                            color=MaterialTheme.colorScheme.onSurface.copy(alpha=0.9f),
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(end=3.dp)

                    )// fin texto 1
                } // fin Aplica row

                OutlinedTextField(
                    value = state.correo,
                    onValueChange = vm::onCorreoChange,
                    label ={Text("Correo",
                        color = ColorScheme.onSurface)},
                    singleLine = true ,
                    modifier = Modifier.fillMaxWidth(0.95f),
                    colors = TextFieldDefaults.colors(  //Colores para los Form
                        focusedContainerColor = Color.Gray, //Casilla de Borde
                        unfocusedContainerColor = Color.White) //Casilla de Fondo
                )//fin de lined text field para usuario

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 0.dp)
                )
                {
                    Text("Contraseña",
                        style =MaterialTheme.typography.bodyLarge.copy(
                            color=MaterialTheme.colorScheme.onSurface.copy(alpha=0.9f),
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(end=8.dp)
                    )// fin texto 2
                }
                OutlinedTextField(
                    value = state.password,
                    onValueChange = vm::onPasswordChange,
                    label ={Text("Contraseña",
                        color = ColorScheme.onSurface)},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray,
                        unfocusedContainerColor = Color.White),
                    singleLine = true ,
                    visualTransformation = if (showPass) VisualTransformation.None else
                        PasswordVisualTransformation(),//es el mono para mostrar contraseña

                    trailingIcon = {
                        TextButton(onClick = {showPass =!showPass})
                        {
                            Text(if (showPass)"\uD83D\uDE48" else "\uD83D\uDE49",
                                fontSize = 30.sp)
                        }
                    },//fin trail
                    modifier = Modifier.fillMaxWidth(0.95f)
                )//fin de lined text field para contraseña



                // agregar un espacio entre la imagen y el boton

                Spacer(modifier = Modifier.height(50.dp))

                Button(onClick = {/* accion futura*/
                    vm.submit { correo ->
                        val Email = Uri.encode(correo)
                        navController.navigate("DrawerMenu/$Email")
                        //Navegar a una pantalla pasando el parametro
                        {//inicio navegar
                            popUpTo("login") { inclusive = true }
                            //No puede volver al login con back

                            //Evite crear una nueva instancia o el misma pagina
                            launchSingleTop = true
                        }//termino navegar

                    }//fin submit
                },
                    enabled = !state.isLoading,
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) //fin button

                {
                    //Text("Presioname")
                    Text (if(state.isLoading)"Validando" else "Iniciar Sesion")

                } // fin boton


                Button(
                    onClick = {
                        // La única acción es navegar a la pantalla de registro
                        navController.navigate("registro")
                        // (Asegúrate que "register_screen" sea la ruta que definiste en AppNav.kt)
                    },
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) //fin button
                {
                    // El texto del botón es simple
                    Text("Registrarse")

                } // fin boton

            }// fin Contenido

        } // Fin inner


    } // fin Aplicar Material
}// Fin HomeScreen



