package com.example.retrofitapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.ZoneOffset


// se puede modificar en base a los valores o parámetros
interface PokeApiService{
    @GET("pokemon")
    fun listPokemon(@Query("limit") limit:Int, @Query("offset") offset: Int ): Call<PokemonListResponse>

    // las siguientes 2 líneas van juntas
    @GET("pokemon/{id}")
    // Call<Pokemon> Tipo genérico de la clase Pokemon
    fun getPokemonById(@Path("id") pokemonId:Int): Call<Pokemon>
}

