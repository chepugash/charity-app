package com.example.categories.data.api.response

class CategoriesResponse : ArrayList<CategoriesResponseItem>()

data class CategoriesResponseItem(
    val id: Int,
    val image: Image,
    val name: String
)

data class Image(
    val src: String
)