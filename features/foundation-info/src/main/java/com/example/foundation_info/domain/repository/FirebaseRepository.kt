package com.example.foundation_info.domain.repository

import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.entity.FoundationUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface FirebaseRepository {

    suspend fun getUser(): FoundationUserEntity?

    suspend fun addToFavourite(foundationEntity: FoundationEntity): Task<Void>

    suspend fun removeFromFavourite(foundationEntity: FoundationEntity): Task<Void>

    suspend fun getFavourite(): Task<ArrayList<Long>>
}