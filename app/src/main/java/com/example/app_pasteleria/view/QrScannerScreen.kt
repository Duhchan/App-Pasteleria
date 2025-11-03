package com.example.app_pasteleria.view



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.app_pasteleria.viewmodel.QrViewModel
import com.example.app_pasteleria.utils.QrScanner



@Composable
fun QrScannerScreen(
    viewModel: QrViewModel,
    hasCameraPermission: Boolean,
    onRequestPermission: () -> Unit,
    navController: NavController
) {

    val context = LocalContext.current
    var isScanning by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!hasCameraPermission) {
            Text(
                "Permiso de cámara requerido",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "Para escanear códigos QR, necesitamos acceso a la cámara",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onRequestPermission
            ) {
                Text("Conceder permiso de cámara")
            }
        } else if (isScanning) {
            Text(
                "Escanea un código QR",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Usar el nuevo scanner con CameraX
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                QrScanner(
                    navController = navController,
                    onQrCodeScanned = { qrContent ->
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("qr_result", qrContent)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxSize())

                // Overlay (No cambia)
                Surface(
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.Center),
                    color = Color.Transparent,
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(
                        2.dp,
                        MaterialTheme.colorScheme.primary
                    )
                ) {}
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "Enfoca el código QR en el marco central",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}