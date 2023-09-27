package com.example.einloggohnegoogle.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catfactsfriday.data.database.FactsItemDatabase
import com.example.catfactsfriday.data.datamodels.FactsItem
import com.example.catfactsfriday.data.remote.ItemApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "AppRepositoryTAG"

class AppRepository(private val api: ItemApi, private val database: FactsItemDatabase) {

    private val _items = MutableLiveData<List<FactsItem>>()

    val items: LiveData<List<FactsItem>>
        get() = _items

    suspend fun getItems() {
        try {
            val itemData = api.retrofitService.getItems()
            _items.postValue(listOf(itemData))
            insertFactsFromApi(itemData)
            Log.d(TAG, "getItems Data: $itemData")
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }

    suspend fun insertFactsFromApi(itemData: FactsItem) {
        try {
            withContext(Dispatchers.IO) {
                Log.d(TAG, "getItems Data: $itemData, $itemData")
                database.dao.insertAll(itemData)
                Log.d("items", "Inserted $itemData")
            }
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Error inserting facts from API into database: $e")
        }
    }

    suspend fun getAll() {
        try {
            val items = withContext(Dispatchers.IO) {
                database.dao.getAllItems()
            }
            Log.d("msgtaggetall", "getall: $items")
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Fehler beim Abrufen aller Elemente: $e")
        }
    }
}
