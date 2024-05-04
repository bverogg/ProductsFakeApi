package com.example.retrofitapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.ZoneOffset

// se puede modificar en base a los valores o parámetros
interface ProductApiService {
    @GET("products")
    //fun listProductos(@Query("limit") limit:Int, @Query("offset") offset: Int ): Call<ProductListResponse>
    fun getProducts(): Call<List<Product>>

    // las siguientes 2 líneas van juntas
    @GET("products/{id}")
    // Call<Pokemon> Tipo genérico de la clase Pokemon
    fun getProductById(@Path("id") productId:Int): Call<Product>


}