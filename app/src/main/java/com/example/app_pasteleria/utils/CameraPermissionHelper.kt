package com.example.app_pasteleria.utils

import android.Manifestimport android.content.Context
import android.content.pm.PackageManager

object CameraPermissionHelper {
    fun hasCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
}