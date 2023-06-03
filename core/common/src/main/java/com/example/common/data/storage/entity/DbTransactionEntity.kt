package com.example.common.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "transactions",
    foreignKeys = [ForeignKey(
        entity = DbUserEntity::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = DbFoundationEntity::class,
            parentColumns = ["id"],
            childColumns = ["foundation_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class DbTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Date,
    val sum: Long,
    @ColumnInfo(name = "foundation_id") val foundationId: Long,
    @ColumnInfo(name = "user_id") val userId: String
)
