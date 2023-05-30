package com.example.foundation_info.domain.usecase

import com.example.foundation_info.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class GetFavouriteUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(): Task<ArrayList<Long>> = firebaseRepository.getFavourite()
}