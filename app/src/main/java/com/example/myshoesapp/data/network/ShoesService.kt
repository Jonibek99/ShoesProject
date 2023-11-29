package com.example.myshoesapp.data.network

import com.example.myshoesapp.data.network.response.MyItemResponse
import com.example.myshoesapp.data.network.response.MyListResponse
import com.example.myshoesapp.data.network.shoes.ShoesRequest
import com.example.myshoesapp.data.network.shoes.ShoesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
// postman
interface ShoesService {
    @GET("records/all")
    suspend fun getAllShoes(@Query("student_id") student_id: String):
            MyListResponse<ShoesResponse>

    @POST("records")
    suspend fun insertNewShoes(
        @Query("student_id") student_id: String,
        @Body shoesRequest: ShoesRequest
    ): ShoesResponse

    @GET("records/{record_id}")
    suspend fun getOneShoesById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<ShoesResponse>
}