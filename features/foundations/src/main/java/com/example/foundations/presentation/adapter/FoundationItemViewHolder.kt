package com.example.foundations.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foundations.databinding.ItemFoundationBinding
import com.example.foundations.domain.entity.FoundationEntity
import com.example.theme.R

class FoundationItemViewHolder(
    val binding: ItemFoundationBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(foundation: FoundationEntity) {
        with(binding) {
            tvName.text = foundation.name
            ivPreview.load(BASE_URL + foundation.image) {
                crossfade(true)
                crossfade(DURATION)
                placeholder(R.drawable.ic_photo)
            }
        }
    }

    companion object {
        private const val DURATION = 200
        private const val BASE_URL = "http://192.168.144.30:9999/image?name="
    }
}