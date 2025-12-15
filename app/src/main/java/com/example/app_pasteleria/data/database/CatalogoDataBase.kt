package com.example.app_pasteleria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.dao.ComentarioDao
import com.example.app_pasteleria.data.dao.UsuarioDao
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.model.Usuario
import com.example.app_pasteleria.data.model.Comentario // <-- Añadir este


@Database(

    entities = [Catalogo::class, Usuario::class, Comentario::class],
    version = 5,
    exportSchema = false

)

abstract class CatalogoDataBase: RoomDatabase(){
    abstract fun catalogoDao(): CatalogoDao
    abstract fun usuarioDao(): UsuarioDao

    abstract fun comentarioDao(): ComentarioDao

    companion object{
        private var INSTANCE: CatalogoDataBase? = null

        fun getDatabase(context: Context): CatalogoDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatalogoDataBase::class.java,
                    "catalogo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            } // fin return
        } // fin getDatabase
    } // fin companion
} // fin abstract