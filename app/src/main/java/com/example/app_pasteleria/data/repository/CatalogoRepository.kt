package com.example.app_pasteleria.data.repository

import com.example.app_pasteleria.R
import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.remote.RetrofitInstance
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

    // --- API (Catálogo de Tortas) ---
    // Esta función ahora solo devuelve la lista, NO guarda en BD
    suspend fun obtenerTortasDeInternet(): List<Catalogo> {
        try {
            val respuestaApi = RetrofitInstance.api.obtenerTortasDesdeInternet()

            // Convertimos de TortaApi a Catalogo para usarlo en la UI
            return respuestaApi.map { tortaWeb ->
                Catalogo(
                    nombre = tortaWeb.nombre,
                    precio = tortaWeb.precio,
                    descripcion = tortaWeb.descripcion,
                    imagen = obtenerImagenId(tortaWeb.imagenNombre)
                )
            }
        } catch (e: Exception) {
            println("Error API: ${e.message}")
            return emptyList()
        }
    }

    // Función auxiliar para traducir el nombre de la imagen
    private fun obtenerImagenId(nombre: String): Int {
        return when (nombre.lowercase()) {
            "tortachocolate" -> R.drawable.tortachocolate
            "tortafruta" -> R.drawable.tortafruta
            "tortavainilla" -> R.drawable.tortavainilla
            "tortacircularmanjar" -> R.drawable.tortacircularmanjar
            "postremoussechocolate" -> R.drawable.postremoussechocolate
            "postretiramisu" -> R.drawable.postretiramisu
            "tortanaranja" -> R.drawable.tortanaranja
            "cheesecake" -> R.drawable.cheesecake
            "empanadamanzana" -> R.drawable.empanadamanzana
            "pansingluten" -> R.drawable.pansingluten
            "tartasantiago" -> R.drawable.tartasantiago
            "brownie" -> R.drawable.brownie
            "tortaceganachocolate" -> R.drawable.tortaceganachocolate
            "galletaavena" -> R.drawable.galletaavena
            "tortacumpleanios" -> R.drawable.tortacumpleanios
            "tortaboda" -> R.drawable.tortaboda
            else -> R.drawable.logo // Imagen por defecto
        }
    }
}