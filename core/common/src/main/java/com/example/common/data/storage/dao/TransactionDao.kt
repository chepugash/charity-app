package com.example.common.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.data.storage.entity.DbTransactionEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(transaction: DbTransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transactions: List<DbTransactionEntity>)

    @Query("DELETE FROM transactions")
    fun deleteAll()

    @Query("SELECT * FROM transactions")
    fun getAll(): List<DbTransactionEntity>
}