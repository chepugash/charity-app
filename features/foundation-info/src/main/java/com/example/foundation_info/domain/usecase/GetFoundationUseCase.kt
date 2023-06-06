package com.example.foundation_info.domain.usecase

import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.repository.FoundationRepository
import javax.inject.Inject

class GetFoundationUseCase @Inject constructor(
    private val foundationRepository: FoundationRepository
) {

    suspend operator fun invoke(query: Long): FoundationEntity
        = foundationRepository.getFoundation(query)
}