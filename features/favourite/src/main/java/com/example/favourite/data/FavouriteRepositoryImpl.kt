package com.example.favourite.data

import com.example.favourite.data.favourite.FavouriteApi
import com.example.favourite.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val api: FavouriteApi
) : FavouriteRepository {
}