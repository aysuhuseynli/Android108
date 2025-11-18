package com.example.android108

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android108.databinding.ItemViewBinding
import com.example.android108.model.ProductsItem

class PaginatedProductAdapter: PagingDataAdapter<ProductsItem, PaginatedProductAdapter.ProductsViewHolder>(
    ProductDiffCall()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: ProductsViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    class ProductsViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root){

    }
}