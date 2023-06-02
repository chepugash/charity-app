package com.example.favourite.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.favourite.databinding.ItemFoundationBinding
import com.example.favourite.domain.entity.FoundationEntity

class FoundationItemViewHolder(
    private val binding: ItemFoundationBinding,
    private val onFoundationClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(foundation: FoundationEntity) {
        with(binding) {
            tvName.text = foundation.name
            ivPreview.load("http://192.168.144.30:9999/image?name=${foundation.image}") {
                crossfade(true)
            }
            root.setOnClickListener {
                onFoundationClick(foundation.id)
            }
        }
    }
}