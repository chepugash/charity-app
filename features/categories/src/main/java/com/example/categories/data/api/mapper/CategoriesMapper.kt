package com.example.categories.data.api.mapper

import com.example.categories.data.api.response.CategoriesResponse
import com.example.categories.data.api.response.CategoriesResponseItem
import com.example.categories.domain.entity.CategoryEntity

private fun CategoriesResponseItem.toCategoryEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    image = image.src.substringBefore(".")
)

fun CategoriesResponse.toCategoryEntityList(): List<CategoryEntity> = map {
    it.toCategoryEntity()
}