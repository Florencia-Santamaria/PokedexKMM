package com.example.pokedexkmm.repository

import com.example.pokedex.Database
import com.example.pokedexkmm.DatabaseDriverFactory
import com.example.pokedexkmm.data.PokedexResults


class PokedexDBRepository(databaseDriverRepository: DatabaseDriverFactory) {
    private val database = Database(databaseDriverRepository.createDriver())
    private val dbQuerys =database.pokemonQueries

    fun getPokemons(): List<PokedexResults> {
        return dbQuerys.getAllPokemon().executeAsList().map {
            PokedexResults(it.name, it.url)
        }
    }

    fun addPokemon(pokemon: PokedexResults) {
        dbQuerys.insertPokemon(name = pokemon.name , url= pokemon.url)
    }
}