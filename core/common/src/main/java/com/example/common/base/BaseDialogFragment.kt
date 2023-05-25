package com.example.common.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import javax.inject.Inject

abstract class BaseDialogFragment<T : BaseViewModel> : DialogFragment() {

    @Inject
    protected open lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    abstract fun inject()

    abstract fun subscribe(viewModel: T)
}