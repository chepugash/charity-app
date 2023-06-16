package com.example.common.core

import android.content.Context
import androidx.core.content.ContextCompat

class ResourceManager(
    private val context: Context
) {

    fun getString(resource: Int): String {
        return context.getString(resource)
    }

    fun getColor(res: Int): Int {
        return ContextCompat.getColor(context, res)
    }

    fun getQuantityString(id: Int, quantity: Int): String {
        return context.resources.getQuantityString(id, quantity)
    }
}