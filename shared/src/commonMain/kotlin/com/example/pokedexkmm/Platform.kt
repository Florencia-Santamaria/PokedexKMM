package com.example.pokedexkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform