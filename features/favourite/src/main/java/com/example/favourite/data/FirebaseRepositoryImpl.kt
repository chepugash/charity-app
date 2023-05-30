package com.example.favourite.data

import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseApi: FirebaseApi
) : FirebaseRepository