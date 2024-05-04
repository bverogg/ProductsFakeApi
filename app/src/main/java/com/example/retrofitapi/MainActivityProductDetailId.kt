package com.example.retrofitapi

//import retrofit2.Retrofit
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivityProductDetailId : AppCompatActivity(){
    // Iniciar posterior a que ya tenga todos los valores cargados posterior a retrofit
    // para que no truene la aplicación si no está inicializada la aplicación a consumir
    private lateinit var textViewProductName: TextView
    private lateinit var textViewProductCategory: TextView
    private lateinit var textViewProductPrice: TextView
    private lateinit var textViewProductDescription: TextView
    private lateinit var imageViewProduct: ImageView

    var idProductoDetail : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetailid)

        textViewProductName = findViewById(R.id.text_product_name)
        textViewProductCategory= findViewById(R.id.text_product_category)
        textViewProductPrice= findViewById(R.id.text_product_price)
        imageViewProduct = findViewById(R.id.image_product)
        textViewProductDescription= findViewById(R.id.text_product_description)

        val boton = findViewById<Button>(R.id.boton_send)
        val botonInicio = findViewById<Button>(R.id.boton_inicio)

        boton.setOnClickListener{
            // permite moverte de una página a otra this = context dónde estoy posicionado
            val intent = Intent(this, MainActivityProductId::class.java)
            intent.putExtra("idProducto", idProductoDetail)
            startActivity(intent)
        }

        botonInicio.setOnClickListener{
            val intent = Intent(this, MainActivityInicial::class.java)
           startActivity(intent)
        }

        // Configuración de Retrofig
        // retrofit es una instancia, manda llamar un constructor
        val retrofit: Retrofit = Retrofit.Builder()
            //.baseUrl("https://pokeapi.co/api/v2/")
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Tipando el servicio de pokeApiService
        // modelado con java
        val productApiService = retrofit.create(ProductApiService::class.java)

        // Hacer una solicitud asíncrona a la API
        // mandar llamar al pokemon
        // callback hace la llamada
        // call trae los datos
        val idProducto = intent.getIntExtra("idProducto",0)
        idProductoDetail = idProducto
        productApiService.getProductById(idProducto).enqueue(object : Callback<Product>{

            override fun onResponse(call: Call<Product>, response: Response<Product>){
                if(response.isSuccessful){
                    val product = response.body()
                    textViewProductName.text = product?.title
                    textViewProductCategory.text = product?.category
                    textViewProductPrice.text = product?.price.toString()
                    textViewProductDescription.text = product?.description.toString()
                    val img: String? = product?.image
                    Picasso.get().load(img).into(imageViewProduct)


                }
            }
            override fun onFailure(call: Call<Product>, t: Throwable){
                textViewProductName.text = "Error al cargar datos"
            }
        })

    }

}


