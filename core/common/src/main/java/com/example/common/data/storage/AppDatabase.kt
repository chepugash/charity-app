package com.example.common.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.common.data.storage.converter.Converters
import com.example.common.data.storage.dao.FoundationDao
import com.example.common.data.storage.dao.TransactionDao
import com.example.common.data.storage.dao.UserDao
import com.example.common.data.storage.entity.DbFoundationEntity
import com.example.common.data.storage.entity.DbTransactionEntity
import com.example.common.data.storage.entity.DbUserEntity

@Database(
    entities = [DbUserEntity::class,
        DbFoundationEntity::class,
        DbTransactionEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getFoundationDao(): FoundationDao

    abstract fun getTransactionDao(): TransactionDao

    companion object {
        const val DB_NAME = "AppDatabase"
    }
}