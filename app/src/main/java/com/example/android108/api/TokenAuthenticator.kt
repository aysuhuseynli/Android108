package com.example.android108.api

import com.example.android108.TokenStorage
import com.example.android108.model.RefreshRequest
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val apiService: ApiService,
    private val tokenStorage: TokenStorage
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            try {
                val refreshRequest = RefreshRequest(refreshToken = tokenStorage.getRefreshToken())
                val refreshResponse = apiService.refreshAccessToken(refreshRequest)

                if (refreshResponse.isSuccessful) {
                    val body = refreshResponse.body()
                    body?.let {
                        tokenStorage.setTokens(it.accessToken!!, it.refreshToken!!)
                    }
                }
                val request = response.request.newBuilder()
                    .addHeader("Authorization", "Bearer ${tokenStorage.getAccessToken()}")
                    .build()
                request
            } catch (e: Exception){
                tokenStorage.clearTokens()
                null
            }

        }
    }

}