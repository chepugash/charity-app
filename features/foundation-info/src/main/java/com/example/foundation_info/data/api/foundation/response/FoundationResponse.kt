package com.example.foundation_info.data.api.foundation.response

data class FoundationResponse(
    val account: String,
    val address: String,
    val description: String,
    val id: Long,
    val image: Image,
    val name: String,
    val phone: String,
    val website: String
)

class Image(
    val src: String
)