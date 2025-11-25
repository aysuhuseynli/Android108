package com.example.android108

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.android108.api.ApiService
import com.example.android108.api.RetrofitInstance
import com.example.android108.databinding.FragmentLoginBinding
import com.example.android108.model.AuthRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitBtn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val apiService = RetrofitInstance.getInstance().create(ApiService::class.java)
            val loginRequest = AuthRequestBody(password = password, username = username)

            lifecycleScope.launch(Dispatchers.IO) {
                val response = apiService.login(loginRequest = loginRequest)
                if (response.isSuccessful){
                    val data = response.body()
                    RetrofitInstance.token = data?.accessToken ?: ""
                    Log.d("TAG", "onViewCreated: $data")
                }
                withContext(Dispatchers.Main){
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }
    }
}