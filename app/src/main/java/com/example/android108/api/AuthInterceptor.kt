package com.example.android108.api

import com.example.android108.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val tokenStorage: TokenStorage): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = tokenStorage.getAccessToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
}