package com.example.einloggohnegoogle.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TipViewModelFactory(private val application: Application, private val size: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TipViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TipViewModel(application, size) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

