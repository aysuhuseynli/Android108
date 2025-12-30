package com.example.android108.api

import com.example.android108.model.AuthRequestBody

class ApiRepository(private val apiService: ApiService) {

    suspend fun login(loginRequest: AuthRequestBody) = apiService.login(loginRequest)
}