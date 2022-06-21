package com.example.shopapp.features.productDetailsScreen.data.dataSource.local

import androidx.lifecycle.LiveData
import com.example.shopapp.features.productDetailsScreen.data.cacheDB.modelDB.ProductDetailsItemDB


interface PDLocalDataSource {

    suspend fun insertPDItemToCache(productDetailsItemDB: ProductDetailsItemDB)

    suspend fun getProductDetails(): List<ProductDetailsItemDB>

}