package com.example.pokedexkmm.data

interface PokedexClient {
    suspend fun getPokedex(): Pokedex
}