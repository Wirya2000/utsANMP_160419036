package com.ubaya.ukuliner_160419036.model

import java.util.*

data class Restaurant(
    val id: String?,
    val name: String?,
    val address: String?,
    val phone: String?,
    val rating: Float,
    val number_rating: Int,
    val category: String?,
    val photoURL: String
)

data class Menu(
    val id: String?,
    val restaurant_id: String?,
    val name: String?,
    val description: String?,
    val price: Int?,
    val photoURL: String
)

data class User(
    val id: String?,
    val username: String?,
    val name: String?,
    val phone: String?,
    val photoURL: String,
    val email: String?
)

data class Review(
    val id: String?,
    val restaurant_id: String?,
    val username: String?,
    val name: String?,
    val phone: String?,
    val photoURL: String,
    val email: String?,
    val title: String?,
    val content: String?,
    val date: String?,
    val rating: Float?
)