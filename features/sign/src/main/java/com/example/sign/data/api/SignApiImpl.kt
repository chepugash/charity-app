package com.example.sign.data.api

import com.example.sign.domain.entity.AuthResult
import com.example.sign.domain.entity.SignUserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.rpc.context.AttributeContext.Auth
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class SignApiImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SignApi {

    override suspend fun signUp(userEntity: SignUserEntity): AuthResult {
        var result: AuthResult = AuthResult.Success
        auth.createUserWithEmailAndPassword(
                userEntity.email,
                userEntity.password
        ).addOnCompleteListener {
            result = if (it.isSuccessful) {
                AuthResult.Success
            } else {
                AuthResult.Error(it.exception?.message ?: "Error")
            }
        }.await()
        return result
    }

    override suspend fun signIn(userEntity: SignUserEntity): AuthResult {
        var result: AuthResult = AuthResult.Success
        auth.signInWithEmailAndPassword(
            userEntity.email,
            userEntity.password
        ).addOnCompleteListener {
            result = if (it.isSuccessful) {
                AuthResult.Success
            } else {
                AuthResult.Error(it.exception?.message ?: "Error")
            }
        }.await()
        return result
    }
}