package com.example.retrofitapi

//import retrofit2.Retrofit
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.view.LayoutInflater



class MainActivityProducts : AppCompatActivity(){
    // Iniciar posterior a que ya tenga todos los valores cargados posterior a retrofit
    // para que no truene la aplicación si no está inicializada la aplicación a consumir
    private lateinit var textViewProductName: TextView
    private lateinit var textViewProductCategory: TextView
    private lateinit var textViewProductPrice: TextView
    private lateinit var imageViewProduct: ImageView

    var idProductoDesde : Int = 1;
    var idProductoHasta : Int = 20;
    private lateinit var productList: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val botonInicio = findViewById<Button>(R.id.boton_inicio)

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
        // callback hace la llamada
        // call trae los datos
        productApiService.getProducts().enqueue(object : Callback<List<Product>>{

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>){
                if(response.isSuccessful){
                    val products = response.body()
                    val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)

                    val layoutInflater = LayoutInflater.from(this@MainActivityProducts)
                    idProductoDesde = intent.getIntExtra("idProductoDesde",1)
                    idProductoHasta = intent.getIntExtra("idProductoHasta",20)
                    idProductoDesde -=1;

                    productList = (products ?: emptyList()).toMutableList()

                    // Obtenemos una sublista de productos desde  hasta
                    val sublistOfProducts = productList.subList(idProductoDesde, idProductoHasta)

                    //for (product in productList) {
                    for (product in sublistOfProducts) {
                        // se va creando un nuevo itemView en el ciclo
                        val itemView = layoutInflater.inflate(R.layout.item_product, null)
                        val textViewProductId = itemView.findViewById<TextView>(R.id.text_product_id)
                        val textViewProductName = itemView.findViewById<TextView>(R.id.text_product_name)
                        val textViewProductCategory= itemView.findViewById<TextView>(R.id.text_product_category)
                        val textViewProductPrice= itemView.findViewById<TextView>(R.id.text_product_price)
                        val imageViewProduct = itemView.findViewById<ImageView>(R.id.image_product)
                        textViewProductId.text = product.id.toString()
                        textViewProductName.text = product?.title
                        textViewProductCategory.text = product?.category
                        textViewProductPrice.text = product.price?.toString()
                        val img: String? = product?.image
                        Picasso.get().load(img).into(imageViewProduct)

                        itemView.setOnClickListener {
                            // Aquí puedes manejar la navegación al detalle del producto
                            val intent = Intent(this@MainActivityProducts, MainActivityProductDetailId::class.java)
                            intent.putExtra("idProducto", product.id)
                            startActivity(intent)
                        }
                        // aquí va metiendo al linear layout principal cada producto
                        linearLayout.addView(itemView)
                    }
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable){
                textViewProductName.text = "Error al cargar datos"
            }
        })

    }

}


