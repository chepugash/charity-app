package com.example.common.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

fun Activity.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showShortToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

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
