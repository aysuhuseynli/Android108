package com.example.android108

import android.view.LayoutInflater
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
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductsViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        item?.let { holder.bind(item = it) }
    }

    class ProductsViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductsItem) {
            binding.price.text = "$${item.price}"
            binding.title.text = item.title
        }
    }
}