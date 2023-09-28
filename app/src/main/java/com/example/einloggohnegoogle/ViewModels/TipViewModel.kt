package com.example.einloggohnegoogle.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catfactsfriday.data.database.getItemDatabase
import com.example.catfactsfriday.data.datamodels.FactsItem
import com.example.catfactsfriday.data.remote.ItemApi
import com.example.einloggohnegoogle.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TipViewModel(application: Application, val size: Int) : AndroidViewModel(application) {

    private val itemDatabase = getItemDatabase(application)
    private val repository = AppRepository(ItemApi, itemDatabase)

    val items = repository.items
    private val tips: List<FactsItem> = mutableListOf()  // Annahme einer Liste von Tip-Elementen
    fun getItem(index: Int): FactsItem? {
        return if (index in 0 until tips.size) {
            tips[index]
        } else {
            null
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getItems()
            repository.getAll()
        }
    }

    fun insertDataFromApi(itemData: FactsItem) {
        viewModelScope.launch {
            repository.insertFactsFromApi(itemData)
        }
    }


}





