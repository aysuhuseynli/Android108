package com.example.android108.api

import com.example.android108.model.AuthRequestBody
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(loginRequest: AuthRequestBody) = apiService.login(loginRequest)
}