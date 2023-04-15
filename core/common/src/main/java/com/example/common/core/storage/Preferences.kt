package com.example.common.core.storage

interface Preferences {

    fun saveAccessToken(token: String)

    fun getAccessToken(): String
}