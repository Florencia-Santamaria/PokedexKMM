package com.example.pokedexkmm.data

import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    val results: List<PokedexResults>
)
