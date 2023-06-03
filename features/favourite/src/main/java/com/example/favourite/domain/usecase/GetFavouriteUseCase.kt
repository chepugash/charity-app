package com.example.favourite.domain.usecase

import com.example.favourite.domain.entity.FoundationEntity
import com.example.favourite.domain.repository.FavouriteRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class GetFavouriteUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {

    suspend operator fun invoke(): Task<ArrayList<FoundationEntity>> = favouriteRepository.getFavourite()
}