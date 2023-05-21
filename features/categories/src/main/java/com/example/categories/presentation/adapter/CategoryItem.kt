package com.example.categories.presentation.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.categories.databinding.ItemCategoryBinding
import com.example.categories.domain.entity.CategoryEntity

class CategoryItem(
    private val binding: ItemCategoryBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var imageView: ImageView

    fun onBind(category: CategoryEntity) {
        with(binding) {
            tvName.text = category.image
//            "http://192.168.153.30:9999/image?name=01f"
            root.setOnClickListener {

            }
        }
    }
}