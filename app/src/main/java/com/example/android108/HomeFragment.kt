//package com.example.android108
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.view.isVisible
//import androidx.lifecycle.lifecycleScope
//import androidx.paging.LoadState
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.android108.api.ApiService
//import com.example.android108.api.RetrofitInstance
//import com.example.android108.databinding.FragmentHomeBinding
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//@AndroidEntryPoint
//class HomeFragment : Fragment() {
//
//    private lateinit var binding: FragmentHomeBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentHomeBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val apiService = RetrofitInstance.getInstance(requireContext()).create(ApiService::class.java)
//
//        val adapter = PaginatedProductAdapter()
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        val pager = Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { ProductPagingSource(apiService) }
//        )
//
//        lifecycleScope.launch {
//            pager.flow.collect { adapter.submitData(it) }
//        }
//        lifecycleScope.launch {
//            adapter.addLoadStateListener { loadStates ->
//                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
//                binding.progressBar.isVisible = loadStates.append is LoadState.Loading
//            }
//        }
//    }
//}