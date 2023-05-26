package com.example.foundation_info.data.api

import com.example.foundation_info.data.api.response.FoundationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoundationApi {

    @GET("foundation")
    suspend fun getFoundation(
        @Query("id") id: Int
    ): FoundationResponse
}