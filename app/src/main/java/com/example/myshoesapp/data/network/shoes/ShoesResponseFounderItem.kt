package com.example.myshoesapp.data.network.shoes

import com.google.gson.annotations.SerializedName

data class ShoesResponseFounderItem(
    @SerializedName("id")
    val actorEntryId: String,
    @SerializedName("record_id")
    val movieRecordId: String,
    @SerializedName("value")
    val founderName: String
)