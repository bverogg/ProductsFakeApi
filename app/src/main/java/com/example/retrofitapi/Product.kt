package com.example.retrofitapi

data class Product (
    val id:Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val image: String
)

data class ProductListResponse(
    //val count: Int,
    //val next: String?,
    //val previous: String?,
    val results: List<ProductSummary>
)

data class ProductSummary(
    val id:Int,
    val title: String,
    val price: Float,
    val category: String,
    val image: String
)
