package com.example.catfactsfriday.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.catfactsfriday.data.datamodels.FactsItem

@Dao
interface FactsItemDatabaseDao {
    @Insert
    fun insertAll(item: FactsItem)

    @Update
    fun updateItem(item: FactsItem)

    @Delete
    fun deleteItem(item: FactsItem)

    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun getItemById(itemId: Int): FactsItem

    @Query("SELECT * FROM item_table")
    fun getAllItems(): List<FactsItem>
}
