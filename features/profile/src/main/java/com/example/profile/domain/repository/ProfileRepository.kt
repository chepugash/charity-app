package com.example.profile.domain.repository

import com.example.profile.domain.entity.ProfileUserEntity
import com.google.android.gms.tasks.Task

interface ProfileRepository {

    suspend fun getUser(): ProfileUserEntity?

    suspend fun changeName(name: String): Task<Void>?

    suspend fun changePassword(password: String): Task<Void>?

    suspend fun deleteProfile(): Task<Void>?

    suspend fun signOut()
}