package com.example.android108

import androidx.recyclerview.widget.DiffUtil
import com.example.android108.model.ProductsItem

class ProductDiffCall: DiffUtil.ItemCallback<ProductsItem>() {
    override fun areItemsTheSame(
        oldItem: ProductsItem,
        newItem: ProductsItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ProductsItem,
        newItem: ProductsItem
    ): Boolean {
        return oldItem == newItem
    }
}