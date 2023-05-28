package com.example.categories.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.categories.databinding.ItemCategoryBinding
import com.example.categories.domain.entity.CategoryEntity

class CategoryAdapter(
    private val onCategoryClick: (Int) -> Unit
) : ListAdapter<CategoryEntity, RecyclerView.ViewHolder>(
    object: DiffUtil.ItemCallback<CategoryEntity>() {
        override fun areItemsTheSame(
            oldItem: CategoryEntity,
            newItem: CategoryEntity
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CategoryEntity,
            newItem: CategoryEntity
        ): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemViewHolder = CategoryItemViewHolder(
        binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onCategoryClick = onCategoryClick
    )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as CategoryItemViewHolder).onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}