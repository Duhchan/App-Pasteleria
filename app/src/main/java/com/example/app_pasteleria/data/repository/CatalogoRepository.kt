package com.example.app_pasteleria.data.repository

import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.model.Catalogo
import kotlinx.coroutines.flow.Flow

class CatalogoRepository (private val catalogoDao: CatalogoDao) {

    suspend fun insertarCatalogo(pastel: Catalogo) {
        catalogoDao.insertarPastel(pastel)
    }

    fun obtenerProductos(): Flow<List<Catalogo>>{
        return catalogoDao.obtenerCatalogo()
    }

    suspend fun eliminarCatalogo(){
        catalogoDao.eliminarCatalogo()
    }
}