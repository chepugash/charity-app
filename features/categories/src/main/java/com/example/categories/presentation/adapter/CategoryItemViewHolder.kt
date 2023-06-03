package com.example.categories.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.theme.R
import com.example.categories.databinding.ItemCategoryBinding
import com.example.categories.domain.entity.CategoryEntity

class CategoryItemViewHolder(
    private val binding: ItemCategoryBinding,
    private val onCategoryClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(category: CategoryEntity) {
        with(binding) {
            tvName.text = category.name
            ivPreview.load(BASE_URL + category.image) {
                crossfade(true)
                crossfade(DURATION)
                placeholder(R.drawable.ic_photo)
            }
            root.setOnClickListener {
                onCategoryClick(category.id)
            }
        }
    }

    companion object {
        private const val DURATION = 200
        private const val BASE_URL = "http://192.168.144.30:9999/image?name="
    }
}