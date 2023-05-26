package com.example.categories.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.categories.databinding.ItemCategoryBinding
import com.example.categories.domain.entity.CategoryEntity

class CategoryItem(
    private val binding: ItemCategoryBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(category: CategoryEntity) {
        with(binding) {
            tvName.text = category.name
            ivPreview.load("http://192.168.21.30:9999/image?name=${category.image}") {
                crossfade(true)
            }
            root.setOnClickListener {
                action(category.id)
            }
        }
    }
}