package com.example.foundation_info.domain.usecase

import com.example.foundation_info.domain.repository.FoundationRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteUseCase @Inject constructor(
    private val foundationRepository: FoundationRepository
) {

    suspend operator fun invoke(): Flow<List<Long>> = foundationRepository.getFavourite()
}