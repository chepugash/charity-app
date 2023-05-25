package com.example.categories.domain.repository

import com.example.categories.domain.entity.CategoryEntity

interface CategoriesRepository {

    suspend fun getCategories(): List<CategoryEntity>
}