package com.example.profile.domain.entity

sealed class ApiResult {

    object Success: ApiResult()

    data class Error(val message: String): ApiResult()
}