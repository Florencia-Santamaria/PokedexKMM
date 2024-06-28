package com.example.pokedexkmm.repository

import com.example.pokedexkmm.data.Pokedex
import com.example.pokedexkmm.data.PokedexClient
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

open class PokedexRepository  {
    val httpClient = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HttpClient", message = message)
                }
            }
            logger
        }
    }

     suspend fun getPokedex(): Pokedex {
        val result = httpClient.get("https://pokeapi.co/api/v2/pokemon/?limit=800")
        return result.body()
    }
}