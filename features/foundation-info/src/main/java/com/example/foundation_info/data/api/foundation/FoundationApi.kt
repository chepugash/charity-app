package com.example.foundation_info.data.api.foundation

import com.example.foundation_info.data.api.foundation.response.FoundationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoundationApi {

    @GET("foundation")
    suspend fun getFoundation(
        @Query("id") id: Long
    ): FoundationResponse
}