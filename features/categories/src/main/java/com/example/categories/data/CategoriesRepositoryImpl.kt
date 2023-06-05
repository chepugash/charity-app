package com.example.categories.data

import com.example.categories.data.api.CategoriesApi
import com.example.categories.data.api.mapper.toCategoryEntityList
import com.example.categories.domain.entity.CategoryEntity
import com.example.categories.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoriesApi
) : CategoriesRepository {

    override suspend fun getCategories(): List<CategoryEntity> = api.getCategories()
        .toCategoryEntityList()
}