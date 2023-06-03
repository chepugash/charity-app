package com.example.common.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.common.data.storage.entity.DbUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun save(user: DbUserEntity)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): Flow<DbUserEntity>
}