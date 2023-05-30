package com.example.foundation_info.data.api.firebase

import com.example.foundation_info.domain.entity.FoundationEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

interface FirebaseApi {

    suspend fun getUser(): FirebaseUser?

    suspend fun addToFavourite(foundationEntity: FoundationEntity): Task<Void>

    suspend fun removeFromFavourite(foundationEntity: FoundationEntity): Task<Void>

    suspend fun getFavourite(): Task<ArrayList<Long>>

    suspend fun createUserDocument(): Task<Void>

}