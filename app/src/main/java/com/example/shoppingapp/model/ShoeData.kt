package com.example.shoppingapp.model

data class ShoeData(
    val brands : List<String?>?,
    val products : List<Product?>?
)
data class Product(
    val title : String?,
    val img : String?,
    val subtitle : String?,
    val price :Float?,
    val rating: Float?,
)