package com.example.myshoesapp.data.network.shoes

import com.google.gson.annotations.SerializedName


data class ShoesRequest(

    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val founders: List<String>,
    @SerializedName("price")
    val price: Double,
    @SerializedName("size")
    val size: Int,
    @SerializedName("color")
    val gender: String
)
