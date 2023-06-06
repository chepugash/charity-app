package com.example.foundations.domain.usecase

import com.example.foundations.domain.entity.CategoryEntity
import com.example.foundations.domain.repository.FoundationsRepository
import javax.inject.Inject

class GetFoundationsUseCase @Inject constructor(
    private val foundationsRepository: FoundationsRepository
) {

    suspend operator fun invoke(id: Int): CategoryEntity = foundationsRepository.getFoundations(id)
}