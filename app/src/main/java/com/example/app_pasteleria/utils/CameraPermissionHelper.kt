package com.example.app_pasteleria.utils

import android.Manifest
import android.content.Context
import androidx.core.content.ContextCompat


import android.content.pm.PackageManager

object CameraPermissionHelper {
    fun hasCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
}