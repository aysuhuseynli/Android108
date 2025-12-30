package com.example.android108.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android108.TokenStorage
import com.example.android108.UIState
import com.example.android108.api.ApiService
import com.example.android108.model.AuthRequestBody
import com.example.android108.model.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    val apiService: ApiService,
    val tokenStorage: TokenStorage
) : ViewModel() {

    private var _loginState = MutableLiveData<UIState<AuthResponse>>()
    val loginState: MutableLiveData<UIState<AuthResponse>> = _loginState

    fun login(password: String, username: String) {
        _loginState.value = UIState.Loading
        viewModelScope.launch {
            val loginRequest = AuthRequestBody(password, username)
            try {
                val response = withContext(Main){
                    apiService.login(loginRequest)
                }
                if (response.isSuccessful) {
                    val data = response.body()
                    tokenStorage.setTokens(data?.accessToken!!, data.refreshToken!!)
                    _loginState.value = UIState.Success(data = data)
                }
            } catch (e: Exception) {
                _loginState.value = UIState.Error(message = e.message.toString())
            }

        }
    }
}