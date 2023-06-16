package com.example.profile.data.api

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

interface ProfileApi {

    suspend fun getUser(): FirebaseUser?

    suspend fun changeUserName(name: String): Task<Void>?

    suspend fun changeUserPassword(password: String): Task<Void>?

    suspend fun deleteProfile(): Task<Void>?

    suspend fun signOut()
}