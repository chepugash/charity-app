package com.example.common.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.data.storage.entity.DbFoundationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoundationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(foundations: List<DbFoundationEntity>)

    @Query("DELETE FROM favorite_foundations")
    fun deleteAll()

    @Query("SELECT * FROM favorite_foundations")
    fun getAll(): Flow<List<DbFoundationEntity>>
}