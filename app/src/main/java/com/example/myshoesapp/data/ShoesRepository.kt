package com.example.myshoesapp.data

import android.util.Log
import com.example.myshoesapp.data.network.RetrofitInstance
import com.example.myshoesapp.data.network.response.MyItemResponse
import com.example.myshoesapp.data.network.response.MyListResponse
import com.example.myshoesapp.data.network.shoes.ShoesRequest
import com.example.myshoesapp.data.network.shoes.ShoesResponse
import com.example.myshoesapp.data.network.shoes.ShoesResponseFounderItem
import com.example.myshoesapp.models.Shoes
class ShoesRepository {
    suspend fun getShoesList(): List<Shoes> {
        val shoes = mutableListOf<Shoes>()

        try {

            val response: MyListResponse<ShoesResponse> =
                RetrofitInstance.shoesService.getAllShoes("09974")
            val shoesFromResponse = response.data

            if (shoesFromResponse != null) {
                for (shoesFromResponse in shoesFromResponse) {
                    if (shoesFromResponse.description != null) {
                        shoes.add(
                            Shoes(
                                title = shoesFromResponse.name.uppercase(),
                                description = shoesFromResponse.description.toString(),
                                founders = shoesFromResponse.founders as List<String>,
                                gender = shoesFromResponse.gender,
                                size = shoesFromResponse.size,
                                price = shoesFromResponse.price
                            )
                        )
                    }
                }

            }
        }   catch (ex: Exception){
            ex.printStackTrace()
        }

        return shoes
    }

    suspend fun insertNewShoes(Shoes: Shoes): ShoesResponse? {
        val response: ShoesResponse

        try {
            val shoesRequest =
                ShoesRequest(
                    title = Shoes.title,
                    description = Shoes.description,
                    founders = Shoes.founders,
                    gender = Shoes.gender,
                    price = Shoes.price,
                    size = Shoes.size
                )

            response = RetrofitInstance.shoesService.insertNewShoes(
                "09974",
                shoesRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }

    suspend fun getShoesById(shoesId: String): Shoes? {
        try {
            val response: MyItemResponse<ShoesResponse> =
                RetrofitInstance.shoesService.getOneShoesById(shoesId, "shoes")
            val shoesFromResponse = response.data

            if (shoesFromResponse != null) {
                if (shoesFromResponse.description != null
                ) {
                    return Shoes(
                        id = shoesId,
                        title = shoesFromResponse.name,
                        description = shoesFromResponse.description,
                        founders = extractListOfFoundersFromResponse(shoesFromResponse.founders as List<String>),
                        price = shoesFromResponse.price,
                        gender = shoesFromResponse.gender,
                        size = shoesFromResponse.size,




                    )
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    private fun extractListOfFoundersFromResponse(
        foundersFromResponse: List<String>
    ): List<String> {

        val myFounders = mutableListOf<String>()

        for (founderObj in foundersFromResponse) {
            myFounders.add(founderObj)
        }

        return myFounders
    }
}
