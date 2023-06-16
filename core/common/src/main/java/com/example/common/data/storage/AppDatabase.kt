package com.example.common.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.common.data.storage.dao.FoundationDao
import com.example.common.data.storage.entity.DbFoundationEntity

@Database(
    entities = [DbFoundationEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFoundationDao(): FoundationDao

    companion object {
        const val DB_NAME = "AppDatabase"
    }
}