package com.example.shoppingapp

import com.example.shoppingapp.model.ShoeData
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/4b0537f1-0458-46ed-ba0b-957b67d1f362")
    suspend fun getData() : ShoeData
}