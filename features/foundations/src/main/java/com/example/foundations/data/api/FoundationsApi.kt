package com.example.foundations.data.api

import com.example.foundations.data.api.response.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoundationsApi {

    @GET("categories/category")
    suspend fun getCategory(
        @Query("id") id: Int
    ): CategoryResponse
}