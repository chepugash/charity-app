package com.example.sign.data.api

import com.example.sign.domain.entity.AuthResult
import com.example.sign.domain.entity.SignUserEntity

interface SignApi {

    suspend fun signUp(userEntity: SignUserEntity): AuthResult

    suspend fun signIn(userEntity: SignUserEntity): AuthResult
}