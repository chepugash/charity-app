package com.example.foundation_info.data.api.response

data class FoundationResponse(
    val account: String,
    val address: String,
    val description: String,
    val id: Int,
    val image: Image,
    val name: String,
    val phone: String,
    val website: String
)

data class Image(
    val src: String
)