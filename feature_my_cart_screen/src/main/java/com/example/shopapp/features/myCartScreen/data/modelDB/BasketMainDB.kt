package com.example.shopapp.features.myCartScreen.data.modelDB

data class BasketMainDB(
    val _id: String,
    val basket: List<BasketProductDB>,
    val delivery: String,
    val total: Int
)