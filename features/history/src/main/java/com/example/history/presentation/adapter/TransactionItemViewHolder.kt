package com.example.history.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemTransactionBinding
import com.example.history.domain.entity.TransactionEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TransactionItemViewHolder(
    private val binding: ItemTransactionBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(transaction: TransactionEntity) {
        with(binding) {
            tvDate.text = formatDate(transaction.date)
            tvName.text = transaction.foundationName
            tvSum.text = "${transaction.sum}р"
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        return dateFormat.format(date)
    }

    companion object {
        private const val DATE_FORMAT = "dd-MM-yyyy"
    }
}