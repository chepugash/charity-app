package com.example.common.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_foundations",
    foreignKeys = [ForeignKey(
        entity = DbUserEntity::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )])
data class DbFoundationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val description: String,
    val phone: String,
    val address: String,
    val account: String,
    val website: String,
    val image: String,
    @ColumnInfo(name = "user_id") val userId: String
)