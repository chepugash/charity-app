package com.example.categories.domain.usecase

import com.example.categories.domain.entity.CategoryEntity
import com.example.categories.domain.repository.CategoriesRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {

    suspend operator fun invoke(): List<CategoryEntity> = categoriesRepository.getCategories()
}