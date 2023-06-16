package com.example.categories.data.api.response

class CategoriesResponse : ArrayList<CategoriesResponseItem>()

class CategoriesResponseItem(
    val id: Int,
    val image: Image,
    val name: String
)

class Image(
    val src: String
)