package com.example.common.base

import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    protected open lateinit var viewModel: T

    abstract fun inject()

    abstract fun subscribe(viewModel: T)
}