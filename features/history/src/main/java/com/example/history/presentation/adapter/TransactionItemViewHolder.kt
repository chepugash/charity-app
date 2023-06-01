package com.example.history.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemTransactionBinding
import com.example.history.domain.entity.TransactionEntity

class TransactionItemViewHolder(
    private val binding: ItemTransactionBinding,
    private val onTransactionClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(transaction: TransactionEntity) {
        with(binding) {
            tvDate.text = transaction.date.toString()
            tvName.text = transaction.foundationName
            tvSum.text = "${transaction.sum}р"
            root.setOnClickListener {
                onTransactionClick(transaction.id)
            }
        }
    }
}