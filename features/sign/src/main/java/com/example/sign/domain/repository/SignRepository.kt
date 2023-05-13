package com.example.sign.domain.repository

import com.example.sign.domain.entity.AuthResult
import com.example.sign.domain.entity.SignUserEntity

interface SignRepository {

    suspend fun signUp(signUserEntity: SignUserEntity): AuthResult

    suspend fun signIn(signUserEntity: SignUserEntity): AuthResult
}