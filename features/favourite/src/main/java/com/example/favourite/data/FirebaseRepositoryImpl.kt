package com.example.favourite.data

import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.data.firebase.toUserEntity
import com.example.favourite.domain.entity.FoundationEntity
import com.example.favourite.domain.entity.UserEntity
import com.example.favourite.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseApi: FirebaseApi
) : FirebaseRepository {

    override suspend fun getUser(): UserEntity? = firebaseApi.getUser()?.toUserEntity()

    override suspend fun createUserDocument(): Task<Void> = firebaseApi.createUserDocument()

    override suspend fun getFavourite(): Task<ArrayList<FoundationEntity>> = firebaseApi.getFavourite()
}