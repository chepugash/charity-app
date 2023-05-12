package com.example.sign.data

import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.entity.SignUserFields
import com.example.sign.domain.exception.EmptyFieldException
import com.example.sign.domain.exception.PasswordMismatchException
import com.example.sign.domain.repository.SignRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val signApi: SignApi
) : SignRepository {

    override suspend fun signUp(signUserEntity: SignUserEntity) {
        return null!!
    }
}