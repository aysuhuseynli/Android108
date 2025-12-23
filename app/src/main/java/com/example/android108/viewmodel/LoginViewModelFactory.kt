package com.example.android108.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android108.TokenStorage
import com.example.android108.api.ApiService

class LoginViewModelFactory(
    val apiService: ApiService,
    val tokenStorage: TokenStorage
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(apiService, tokenStorage) as T
    }
}