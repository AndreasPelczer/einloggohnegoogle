package com.example.einloggohnegoogle.ViewModels.MenuViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.einloggohnegoogle.data.datamodels.MenuState


class MenuViewModel : ViewModel() {
    private val _menuState = MutableLiveData<MenuState>()
    val menuState: LiveData<MenuState>
        get() = _menuState

    init {
        // menuzustand setzen.//
        _menuState.value = MenuState(isOpen = false)
    }

    fun openMenu() {
        _menuState.value = MenuState(isOpen = true)
    }

    fun closeMenu() {
        _menuState.value = MenuState(isOpen = false)
    }
}