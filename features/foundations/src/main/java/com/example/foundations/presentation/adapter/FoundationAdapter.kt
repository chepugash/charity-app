package com.example.foundations.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foundations.databinding.ItemFoundationBinding
import com.example.foundations.domain.entity.FoundationEntity

class FoundationAdapter(
    private val onFoundationClick: (Int) -> Unit
) : ListAdapter<FoundationEntity, RecyclerView.ViewHolder>(
    object: DiffUtil.ItemCallback<FoundationEntity>() {
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
    ): FoundationItem = FoundationItem(
        binding = ItemFoundationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onFoundationClick = onFoundationClick
    )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as FoundationItem).onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

}