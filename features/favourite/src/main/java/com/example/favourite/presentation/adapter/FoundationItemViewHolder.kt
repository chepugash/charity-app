package com.example.favourite.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.favourite.databinding.ItemFoundationBinding
import com.example.favourite.domain.entity.FoundationEntity
import com.example.theme.R

class FoundationItemViewHolder(
    private val binding: ItemFoundationBinding,
    private val onFoundationClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(foundation: FoundationEntity) {
        with(binding) {
            tvName.text = foundation.name
            ivPreview.load(BASE_URL + foundation.image) {
                crossfade(true)
                crossfade(DURATION)
                placeholder(R.drawable.ic_photo)
            }
            root.setOnClickListener {
                onFoundationClick(foundation.id)
            }
        }
    }

    companion object {
        private const val DURATION = 200
        private const val BASE_URL = "http://192.168.144.30:9999/image?name="
    }
}