package com.example.android108

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android108.api.ApiService
import com.example.android108.api.RetrofitInstance
import com.example.android108.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val apiService = RetrofitInstance.getInstance().create(ApiService::class.java)
//
//        val adapter = PaginatedProductAdapter()
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//
//        Log.d("TAG", "onCreate1: ")
//
//        val pager = Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { ProductPagingSource(apiService)}
//        )
//
//        Log.d("TAG", "onCreate: ${pager.liveData.value}")
//        lifecycleScope.launch {
//            Log.d("TAG", "onCreate:")
//            pager.flow.collect { adapter.submitData(it) }
//        }
//        lifecycleScope.launch {
//            adapter.addLoadStateListener { loadStates ->
//                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
//                binding.progressBar.isVisible = loadStates.append is LoadState.Loading
//            }
//        }
    }
}