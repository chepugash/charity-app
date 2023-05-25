package com.example.foundations.data.api.response

data class CategoryResponse(
    val foundations: List<Foundation>,
    val id: Int,
    val name: String
)

data class Foundation(
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