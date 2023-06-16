package com.example.sign.domain.entity

sealed class ApiResult {

    object Success: ApiResult()

    class Error(val message: String): ApiResult()
}