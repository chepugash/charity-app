package com.example.favourite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.favourite.databinding.ItemFoundationBinding
import com.example.favourite.domain.entity.FoundationEntity

class FoundationAdapter(
    private val onFoundationClick: (Long) -> Unit
) : ListAdapter<FoundationEntity, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<FoundationEntity>() {
        override fun areItemsTheSame(
            oldItem: FoundationEntity,
            newItem: FoundationEntity
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: FoundationEntity,
            newItem: FoundationEntity
        ): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoundationItemViewHolder = FoundationItemViewHolder(
        binding = ItemFoundationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        setupActions(this, this.binding, onFoundationClick)
    }

    private fun setupActions(
        viewHolder: RecyclerView.ViewHolder,
        binding: ItemFoundationBinding,
        onFoundationClick: (Long) -> Unit
    ) {
        binding.root.setOnClickListener {
            val foundationItem = getItem(viewHolder.adapterPosition)
            onFoundationClick(foundationItem.id)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as FoundationItemViewHolder).onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

}