package com.example.sign.domain.entity

sealed class AuthResult {

    object Success: AuthResult()

    data class Error(val message: String): AuthResult()
}