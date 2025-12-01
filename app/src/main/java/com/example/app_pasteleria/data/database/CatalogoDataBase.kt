package com.example.app_pasteleria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.dao.UsuarioDao
import com.example.app_pasteleria.data.model.Catalogo
import com.example.app_pasteleria.data.model.Usuario

@Database(
<<<<<<< HEAD
    entities = [Catalogo::class],
    version = 3,
    exportSchema = false // evite warning
=======
    entities = [Catalogo::class, Usuario::class],
    version = 3,
    exportSchema = false
>>>>>>> f004ecc1af9cd0cfb2b9b4e72060067d525672b5
)

abstract class CatalogoDataBase: RoomDatabase(){
    abstract fun catalogoDao(): CatalogoDao
    abstract fun usuarioDao(): UsuarioDao
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