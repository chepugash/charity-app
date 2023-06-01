package com.example.history.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemTransactionBinding
import com.example.history.domain.entity.TransactionEntity

class TransactionItemViewHolder(
    private val binding: ItemTransactionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(transaction: TransactionEntity) {
        with(binding) {
            tvDate.text = transaction.date.toString()
            tvName.text = transaction.foundationName
            tvSum.text = "${transaction.sum}р"
        }
    }
}