package com.example.pokedexkmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexkmm.repository.PokedexRepository

class PokedexViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexRepository = PokedexRepository()


        return PokedexViewModel(pokedexRepository) as T
    }
}
