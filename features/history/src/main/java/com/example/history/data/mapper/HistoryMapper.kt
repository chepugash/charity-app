package com.example.history.data.mapper

import com.example.history.domain.entity.TransactionEntity
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private fun HashMap<String, out Any>.toTransactionEntity(): TransactionEntity {
    val date = get("date") as Timestamp
    return TransactionEntity(
        date = date.toDate(),
        foundationName = get("foundationName") as String,
        sum = get("sum") as Long
    )
}

fun Flow<List<HashMap<String, out Any>>>.toTransactionEntityList(): Flow<List<TransactionEntity>> =
    map { list ->
        list.map { entity ->
            entity.toTransactionEntity()
        }
    }