package com.example.pokedexkmm.repository

import com.example.pokedexkmm.DatabaseDriverFactory


class PokedexDBRepository(databaseDriverRepository: DatabaseDriverFactory) {
    private val database = PokedexDatabase(databaseDriverRepository.createDriver())

}