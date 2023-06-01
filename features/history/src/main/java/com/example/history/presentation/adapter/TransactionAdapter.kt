package com.example.history.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemTransactionBinding
import com.example.history.domain.entity.TransactionEntity

class TransactionAdapter(
    private val onTransactionClick: (Long) -> Unit
) : androidx.recyclerview.widget.ListAdapter<TransactionEntity, RecyclerView.ViewHolder>(
    object: DiffUtil.ItemCallback<TransactionEntity>() {
        override fun areItemsTheSame(
            oldItem: TransactionEntity,
            newItem: TransactionEntity
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TransactionEntity,
            newItem: TransactionEntity
        ): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionItemViewHolder = TransactionItemViewHolder(
        binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onTransactionClick = onTransactionClick
    )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as TransactionItemViewHolder).onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

}