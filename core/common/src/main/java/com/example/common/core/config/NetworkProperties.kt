package com.example.common.core.config

data class NetworkProperties(
    val connectTimeout: Long,
    val readTimeout: Long,
    val writeTimeout: Long
)