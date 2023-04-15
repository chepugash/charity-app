package com.example.sign_up.domain.repository

import com.example.sign_up.domain.entity.SignUpUserEntity

interface SignUpRepository {

    suspend fun signUp(signUpUserEntity: SignUpUserEntity)
}