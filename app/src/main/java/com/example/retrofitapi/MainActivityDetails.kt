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


class MainActivityDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitydetail)

        val botonback = findViewById<Button>(R.id.boton_back)
        botonback.setOnClickListener{
            // permite moverte de una página a otra this = context dónde estoy posicionado
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }







}