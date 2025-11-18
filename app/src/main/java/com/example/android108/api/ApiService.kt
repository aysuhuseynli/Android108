package com.example.android108.api

import com.example.android108.model.PaginatedProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products/{aysu}")
    suspend fun getPaginatedProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<PaginatedProductResponse>
}