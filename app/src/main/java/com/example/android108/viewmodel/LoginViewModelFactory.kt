package com.example.android108.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android108.TokenStorage
import com.example.android108.api.ApiRepository
import com.example.android108.api.ApiService

class LoginViewModelFactory(
    val apiRepository: ApiRepository,
    val tokenStorage: TokenStorage
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(apiRepository, tokenStorage) as T
    }
}