package com.example.pokedexkmm.data

import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokedexResults>
)
