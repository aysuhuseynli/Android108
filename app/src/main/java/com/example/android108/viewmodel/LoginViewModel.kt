package com.example.android108.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android108.TokenStorage
import com.example.android108.UIState
import com.example.android108.api.ApiRepository
import com.example.android108.model.AuthRequestBody
import com.example.android108.model.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val apiRepository: ApiRepository,
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
                    apiRepository.login(loginRequest)
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