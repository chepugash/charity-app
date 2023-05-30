package com.example.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.common.utils.showSnackbar
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    protected open lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    abstract fun inject()

    abstract fun subscribe(viewModel: T)
}