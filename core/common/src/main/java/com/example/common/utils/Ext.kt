package com.example.common.utils

import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.showToast(
    @StringRes message: Int,
    duration: Int = Toast.LENGTH_LONG
) = Toast
    .makeText(this.context, message, duration)
    .show()

fun View.showToast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_LONG
) = Toast
    .makeText(this.context, message, duration)
    .show()
