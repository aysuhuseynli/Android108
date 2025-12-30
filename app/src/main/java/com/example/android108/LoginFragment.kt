package com.example.android108

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.android108.api.ApiRepository
import com.example.android108.api.ApiService
import com.example.android108.api.RetrofitInstance
import com.example.android108.databinding.FragmentLoginBinding
import com.example.android108.model.AuthRequestBody
import com.example.android108.model.AuthResponse
import com.example.android108.viewmodel.LoginViewModel
import com.example.android108.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var tokenStorage: TokenStorage

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

        tokenStorage = TokenStorage(requireContext())
        val apiService =
            RetrofitInstance.getInstance(requireContext()).create(ApiService::class.java)
        val apiRepository = ApiRepository(apiService)

        viewModel = ViewModelProvider.create(
            this,
            LoginViewModelFactory(apiRepository, tokenStorage)
        )[LoginViewModel::class]

        binding.submitBtn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            viewModel.login(password, username)
        }

        observeLoginState()
    }

    fun observeLoginState() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UIState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

                is UIState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }
}