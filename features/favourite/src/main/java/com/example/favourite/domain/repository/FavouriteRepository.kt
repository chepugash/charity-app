package com.example.favourite.domain.repository

import com.example.favourite.domain.entity.FoundationEntity
import com.example.favourite.domain.entity.UserEntity
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    suspend fun getUser(): UserEntity?

    suspend fun createUserDocument(): Task<Void>

    suspend fun getFavourite(): Task<ArrayList<FoundationEntity>>

    fun getFavouriteFromDb(): Flow<List<FoundationEntity>>
}