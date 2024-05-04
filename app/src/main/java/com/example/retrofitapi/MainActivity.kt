package com.example.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
//import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    // Iniciar posterior a que ya tenga todos los valores cargados posterior a retrofit
    // para que no truene la aplicación si no está inicializada la aplicación a consumir
    private lateinit var textViewPokemonName: TextView
    private lateinit var textViewPokemonDescription: TextView
    private lateinit var imageViewPokemon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPokemonName = findViewById(R.id.text_pokemon_name)
        textViewPokemonDescription = findViewById(R.id.text_pokemon_description)
        imageViewPokemon = findViewById(R.id.image_pokemon)
        val boton = findViewById<Button>(R.id.boton_send)



        // Configuración de Retrofig
        // retrofit es una instancia, manda llamar un constructor
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Tipando el servicio de pokeApiService
        // modelado con java
        val pokeApiService = retrofit.create(PokeApiService::class.java)

        // Hacer una solicitud asíncrona a la API
        // mandar llamar al pokemon
        // callback hace la llamada
        // call trae los datos
        pokeApiService.getPokemonById(8).enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>){
                if(response.isSuccessful){
                    val pokemon = response.body()
                    textViewPokemonName.text = pokemon?.name
                    //textViewPokemonDescription.text = pokemon?.id
                   // imageViewPokemon.setImageResource(pokemon?.sprites.front_default)
                }
            }
            override fun onFailure(call: Call<Pokemon>, t: Throwable){
                textViewPokemonName.text = "Error al cargar datos"
            }
        })
        boton.setOnClickListener{
            // permite moverte de una página a otra this = context dónde estoy posicionado
            val intent = Intent(this, MainActivityDetails::class.java)
            startActivity(intent)
        }
    }






}