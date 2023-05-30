package com.example.foundations.data.api.response

class CategoryResponse(
    val foundations: List<Foundation>,
    val id: Int,
    val name: String
)

class Foundation(
    val id: Long,
    val image: Image,
    val name: String,
)

class Image(
    val src: String
)