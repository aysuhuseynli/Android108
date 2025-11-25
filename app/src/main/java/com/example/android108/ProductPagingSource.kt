package com.example.android108

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android108.api.ApiService
import com.example.android108.model.ProductsItem

class ProductPagingSource(
    private val apiService : ApiService
): PagingSource<Int, ProductsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ProductsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductsItem> {
        return try {
            val page = params.key ?: 0
            val limit = 10
            val skip = page * limit

            val response = apiService.getPaginatedProducts(limit, skip)
            val products = response.body()?.products?.filterNotNull() ?: emptyList()

            Log.d("TAG", "load: $products")
            LoadResult.Page(
                data = products,
                prevKey = if (page == 0) null else page - 1 ,
                nextKey = if (products.isEmpty()) null else page + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}