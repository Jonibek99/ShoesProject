package com.example.myshoesapp.models


data class Shoes(
    val id: String = "",
    val title: String,
    val description: String,
    val founders: List<String>,
    val gender: String,
    val size: Int,
    val price: Double,
    )
