package com.example.einloggohnegoogle.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface RezeptDataBaseDao {

    @Insert
    fun insertAndGetId(rezept: Rezept): Long

    @Update
    fun updateRezept(rezept: Rezept)

    @Query("SELECT * FROM rezept_table WHERE id = :itemId")
    fun getItemById(itemId: String): LiveData<Rezept>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg rezept: Rezept)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(rezept: Rezept)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRezept(rezept: Rezept)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(item: Rezept)

    @Query("SELECT * FROM rezept_table")
    fun getAllRezepte(): LiveData<List<Rezept>>

    @Query("DELETE FROM rezept_table")
    fun deleteAll()

    @Query("DELETE FROM rezept_table")
    fun deleteAllRezepte()


    @Query("SELECT * FROM rezept_table")
    fun getAllItems(): LiveData<List<Rezept>>
}
