package com.example.common.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkApiCreator(
    private val okHttpClient: OkHttpClient,
    private val gsonConverterFactory: GsonConverterFactory,
    private val baseUrl: String,
) {

    fun <T> create(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(service)
    }
}