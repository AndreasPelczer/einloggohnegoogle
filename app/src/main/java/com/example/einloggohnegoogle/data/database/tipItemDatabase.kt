package com.example.catfactsfriday.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catfactsfriday.data.datamodels.FactsItem

@Database(entities = [FactsItem::class], version = 1)
abstract class FactsItemDatabase : RoomDatabase() {
    abstract val dao: FactsItemDatabaseDao
}

private lateinit var INSTANCE : FactsItemDatabase

fun getItemDatabase(context: Context): FactsItemDatabase {

    synchronized(FactsItemDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                FactsItemDatabase::class.java,
                "item_table"
            ).build()
        }
        return INSTANCE
    }
}