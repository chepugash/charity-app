package com.example.foundation_info.data

import com.example.foundation_info.data.api.firebase.FirebaseApi
import com.example.foundation_info.data.api.firebase.toFoundationUserEntity
import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.entity.FoundationUserEntity
import com.example.foundation_info.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseApi: FirebaseApi
) : FirebaseRepository {

    override suspend fun getUser(): FoundationUserEntity? = firebaseApi.getUser()
        ?.toFoundationUserEntity()

    override suspend fun addToFavourite(
        foundationEntity: FoundationEntity
    ): Task<Void> = firebaseApi.addToFavourite(foundationEntity)

    override suspend fun removeFromFavourite(
        foundationEntity: FoundationEntity
    ): Task<Void> = firebaseApi.removeFromFavourite(foundationEntity)

    override suspend fun createUserDocument(): Task<Void> = firebaseApi.createUserDocument()

    override suspend fun getFavourite(): Task<ArrayList<Long>> = firebaseApi.getFavourite()
}