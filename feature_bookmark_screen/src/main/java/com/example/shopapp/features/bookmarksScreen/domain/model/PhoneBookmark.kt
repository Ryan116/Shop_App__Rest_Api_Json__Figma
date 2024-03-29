package com.example.shopapp.features.bookmarksScreen.domain.model

data class PhoneBookmark(
    val id: Int,
    val title: String,
    val priceWithoutDiscount: Int,
    val discountPrice: Int,
    val picture: String
)