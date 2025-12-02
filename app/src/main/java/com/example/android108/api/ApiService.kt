package com.example.android108.api

import com.example.android108.model.AuthRequestBody
import com.example.android108.model.AuthResponse
import com.example.android108.model.PaginatedProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("auth/products")
    suspend fun getPaginatedProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<PaginatedProductResponse>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: AuthRequestBody): Response<AuthResponse>
}