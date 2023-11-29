package com.example.myshoesapp.data.network.shoes

import com.google.gson.annotations.SerializedName

data class ShoesResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val founders: List<Any>,
    @SerializedName("price")
    val price: Double,
    @SerializedName("age")
    val size: Int,
    @SerializedName("color")
    val gender: String
)

