package com.example.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageView
//import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class MainActivityInicial : AppCompatActivity() {
    // Iniciar posterior a que ya tenga todos los valores cargados posterior a retrofit
    // para que no truene la aplicación si no está inicializada la aplicación a consumir
    private lateinit var valor_idEditText: TextView
    private lateinit var valor_desdeEditText: TextView
    private lateinit var valor_hastaEditText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_inicial)

        val valor_idEditText = findViewById<TextView>(R.id.valorid)
        val valor_desdeEditText = findViewById<TextView>(R.id.valorid_desde)
        val valor_hastaEditText = findViewById<TextView>(R.id.valorid_hasta)
        val boton_verProducto = findViewById<Button>(R.id.boton_verproducto)
        val boton_verProductos = findViewById<Button>(R.id.boton_verproductos)


        boton_verProducto.setOnClickListener {

            if (valor_idEditText.text.isEmpty()) {
                Toast.makeText(this, "Introduce un número en Id", Toast.LENGTH_LONG).show();
            } else {
                val idProducto = valor_idEditText.text.toString().toInt();
                if (idProducto <= 0 || idProducto > 20) {
                    Toast.makeText(this, "Número no válido en Id", Toast.LENGTH_LONG).show();
                } else {
                    val intent = Intent(this, MainActivityProductId::class.java)
                    intent.putExtra("idProducto", idProducto)
                    startActivity(intent)
                }
            }
        }

        boton_verProductos.setOnClickListener {
            if ( valor_desdeEditText.text.isEmpty() || valor_hastaEditText.text.isEmpty() ) {
                Toast.makeText(this, "Introduce los dos números en selección de IDs", Toast.LENGTH_LONG).show();
            }else {
                val idProductoDesde = valor_desdeEditText.text.toString().toInt();
                val idProductoHasta = valor_hastaEditText.text.toString().toInt();
                if (idProductoDesde <= 0 || idProductoDesde > 20 || idProductoHasta <= 0 || idProductoHasta > 20) {
                    Toast.makeText(
                        this,
                        "Número (s) no válido (s) en selección de Ids",
                        Toast.LENGTH_LONG
                    ).show();
                } else {
                    if (idProductoDesde > idProductoHasta) {
                        Toast.makeText(
                            this,
                            "El número inicial debe ser menor que el final",
                            Toast.LENGTH_LONG
                        ).show();
                    } else {
                        val intent = Intent(this, MainActivityProducts::class.java)
                        intent.putExtra("idProductoDesde", idProductoDesde)
                        intent.putExtra("idProductoHasta", idProductoHasta)
                        startActivity(intent)
                    }
                }
            }
        }

    }






}