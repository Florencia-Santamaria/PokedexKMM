package com.example.pokedexkmm.data

import kotlinx.serialization.Serializable

@Serializable
data class PokedexResults(
    val name: String,
    val url: String
)
