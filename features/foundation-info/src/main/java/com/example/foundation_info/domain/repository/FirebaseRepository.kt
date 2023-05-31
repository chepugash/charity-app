package com.example.foundation_info.domain.repository

import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.entity.UserEntity
import com.google.android.gms.tasks.Task

interface FirebaseRepository {

    suspend fun getUser(): UserEntity?

    suspend fun addToFavourite(foundationEntity: FoundationEntity): Task<Void>

    suspend fun removeFromFavourite(foundationEntity: FoundationEntity): Task<Void>

    suspend fun createUserDocument(): Task<Void>

    suspend fun getFavourite(): Task<ArrayList<Long>>
}