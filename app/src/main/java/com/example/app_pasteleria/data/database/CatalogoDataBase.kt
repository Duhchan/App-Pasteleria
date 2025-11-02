package com.example.app_pasteleria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_pasteleria.data.dao.CatalogoDao
import com.example.app_pasteleria.data.model.Catalogo

@Database(
    entities = [Catalogo::class],
    version = 2,
    exportSchema = false // evite warning
)

abstract class CatalogoDataBase: RoomDatabase(){
    abstract fun catalogoDao(): CatalogoDao

    companion object{
        private var INSTANCE: CatalogoDataBase? = null

        fun getDatabase(context: Context): CatalogoDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatalogoDataBase::class.java,
                    "catalogo_database"
                ).build()
                INSTANCE = instance
                instance
            } // fin return
        } // fin getDatabase
    } // fin companion
} // fin abstract