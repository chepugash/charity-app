package com.example.foundation_info.data

import com.example.foundation_info.data.api.firebase.FirebaseApi
import com.example.foundation_info.data.api.firebase.mapper.toFoundationIdList
import com.example.foundation_info.data.api.firebase.mapper.toFoundationUserEntity
import com.example.foundation_info.data.api.foundation.FoundationApi
import com.example.foundation_info.data.api.foundation.mapper.toFoundationEntity
import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.entity.UserEntity
import com.example.foundation_info.domain.repository.FoundationRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoundationRepositoryImpl @Inject constructor(
    private val foundationApi: FoundationApi,
    private val firebaseApi: FirebaseApi,
) : FoundationRepository {

    override suspend fun getFoundation(foundationId: Long): FoundationEntity =
        foundationApi.getFoundation(foundationId).toFoundationEntity()

    override suspend fun getUser(): UserEntity? =
        firebaseApi.getUser()?.toFoundationUserEntity()

    override suspend fun addToFavourite(foundationEntity: FoundationEntity): Task<Void> =
        firebaseApi.addToFavourite(foundationEntity)

    override suspend fun removeFromFavourite(foundationEntity: FoundationEntity): Task<Void> =
        firebaseApi.removeFromFavourite(foundationEntity)

    override suspend fun getFavourite(): Flow<List<Long>> =
        firebaseApi.getFavourite().toFoundationIdList()
}