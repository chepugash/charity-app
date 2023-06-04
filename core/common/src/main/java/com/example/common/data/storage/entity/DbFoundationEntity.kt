package com.example.common.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_foundations")
data class DbFoundationEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val name: String,
    val description: String,
    val phone: String,
    val address: String,
    val account: String,
    val website: String,
    val image: String,
    @ColumnInfo(name = "user_id") val userId: String
)