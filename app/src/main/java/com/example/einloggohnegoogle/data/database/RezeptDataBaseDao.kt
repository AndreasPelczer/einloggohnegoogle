package com.example.einloggohnegoogle.data.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.einloggohnegoogle.data.datamodels.Rezept


@Dao
interface RezeptDataBaseDao {

    @Insert
    fun insertAndGetId(rezept: Rezept): Long

    @Update
    fun updateRezept(rezept: Rezept)

    @Query("SELECT * FROM rezept_table WHERE id = :itemId")
    fun getItemById(itemId: String):Rezept

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
//@Query("SELECT * FROM posts_table WHERE id = :itemId")
//fun getItemById(itemId: String): PostsData
