package com.example.retrofitapi
//
data class Pokemon (
    val id:Int,
    val name: String,
    val weight: Int,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    val front_default: String
)

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonSummary>
)

data class PokemonSummary(
    val name: String,
    val url: String
)