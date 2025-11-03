package com.example.app_pasteleria.data.repository

import com.example.app_pasteleria.data.model.QrResult

class QrRepository {
    fun processQrContent(content: String): QrResult {
        return QrResult(content)
    }
}