package com.example.pokedexkmm

import app.cash.sqldelight.db.SqlDriver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}