package com.example.sign_up.domain.entity

data class SignUpUserEntity(
    val email: String,
    val password: String,
    val repeatPassword: String
)