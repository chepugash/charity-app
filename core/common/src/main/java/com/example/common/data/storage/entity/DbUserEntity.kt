package com.example.common.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class DbUserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val name: String?
)
