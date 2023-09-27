package com.example.einloggohnegoogle.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.einloggohnegoogle.data.datamodels.Rezept


@Database(entities = [Rezept::class], version = 1)
abstract class RezeptDataBase : RoomDatabase() {
    abstract val dao: RezeptDataBaseDao

}

private lateinit var INSTANCE : RezeptDataBase

fun getRezeptDatabase(context: Context): RezeptDataBase {

    synchronized(RezeptDataBase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                RezeptDataBase::class.java,
                "rezept_table"
            ).build()
        }
        return INSTANCE
    }
}