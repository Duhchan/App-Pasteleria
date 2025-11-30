package com.example.app_pasteleria.data.repository

import android.util.Log
import com.example.app_pasteleria.R
import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class CatalogoRepository(private val catalogoDao: CatalogoDao) {

    // --- MÉTODOS LOCALES (Room) ---
    suspend fun insertarCatalogo(pastel: Catalogo) {
        catalogoDao.insertarPastel(pastel)
    }

    fun obtenerProductos(): Flow<List<Catalogo>> {
        return catalogoDao.obtenerCatalogo()
    }

    suspend fun eliminarCatalogo() {
        catalogoDao.eliminarCatalogo()
    }

    // API (Catálogo de Tortas)
    // trae las tortas para mostrarlas en el menú
    suspend fun obtenerTortasDeInternet(): List<Catalogo> {
        try {
            val respuestaApi = RetrofitInstance.api.obtenerTortasDesdeInternet()
            return respuestaApi.map { tortaWeb ->
                Catalogo(
                    nombre = tortaWeb.nombre,
                    precio = parsearPrecio(tortaWeb.precio),
                    descripcion = tortaWeb.descripcion,
                    imagen = obtenerImagenId(tortaWeb.imagenNombre),
                    cantidad = 1
                )
            }
        } catch (e: Exception) {
            println("Error API: ${e.message}")
            return emptyList()
        }
    }

    //CARRITO EN LA NUBE (Lectura y Escritura)

    //LEER CARRITO
    suspend fun obtenerCarritoNube(): List<Catalogo> {
        return try {
            RetrofitInstance.api.obtenerCarritoNube()
        } catch (e: Exception) {
            emptyList()
        }
    }

    //AGREGAR (Bajar -> Agregar -> Subir)
    suspend fun agregarAlCarritoNube(nuevoPastel: Catalogo) {
        try {
            val listaActual = obtenerCarritoNube().toMutableList()
            listaActual.add(nuevoPastel)
            Log.d("CARRITO", "Intentando subir lista con tamaño: ${listaActual.size}")
            RetrofitInstance.api.sobreescribirCarrito(listaActual)
            Log.d("CARRITO", "Subida exitosa")
        } catch (e: Exception) {
            Log.e("CARRITO", "Error al agregar: ${e.message}")
            e.printStackTrace()
        }
    }

    //ELIMINAR (Bajar -> Borrar -> Subir)
    suspend fun eliminarDelCarritoNube(pastel: Catalogo) {
        try {
            val listaActual = obtenerCarritoNube().toMutableList()
            // Borramos buscando por nombre y descripción para asegurar que es el correcto
            listaActual.removeAll { it.nombre == pastel.nombre && it.descripcion == pastel.descripcion }
            RetrofitInstance.api.sobreescribirCarrito(listaActual)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //ACTUALIZAR CANTIDAD (Bajar -> Editar -> Subir)
    suspend fun actualizarCantidadNube(pastel: Catalogo, nuevaCantidad: Int) {
        try {
            val listaActual = obtenerCarritoNube().toMutableList()
            val index = listaActual.indexOfFirst { it.nombre == pastel.nombre }

            if (index != -1 && nuevaCantidad > 0) {
                val pastelEditado = listaActual[index].copy(cantidad = nuevaCantidad)
                listaActual[index] = pastelEditado
                RetrofitInstance.api.sobreescribirCarrito(listaActual)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parsearPrecio(precioStr: String): Int {
        // Quita signo $, puntos y espacios, y convierte a Int
        return precioStr.replace("$", "").replace(".", "").replace(" ", "").toIntOrNull() ?: 0
    }

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
            else -> R.drawable.logo
        }
    }
}