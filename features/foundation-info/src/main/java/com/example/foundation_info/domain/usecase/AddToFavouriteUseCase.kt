package com.example.foundation_info.domain.usecase

import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.repository.FoundationRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val foundationRepository: FoundationRepository
) {

    suspend operator fun invoke(foundationEntity: FoundationEntity): Task<Void>
        = foundationRepository.addToFavourite(foundationEntity)
}