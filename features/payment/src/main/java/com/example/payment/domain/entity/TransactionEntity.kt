package com.example.payment.domain.entity

import java.util.Date

data class TransactionEntity(
    val date: Date,
    val name: String,
    val sum: Int)