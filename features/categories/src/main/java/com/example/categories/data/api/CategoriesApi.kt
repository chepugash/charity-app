package com.example.categories.data.api

import com.example.categories.data.api.response.CategoriesResponse
import retrofit2.http.GET

interface CategoriesApi {

    @GET("categories")
    suspend fun getCategories(): CategoriesResponse

}