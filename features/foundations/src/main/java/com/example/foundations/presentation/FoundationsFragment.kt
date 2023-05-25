package com.example.foundations.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.foundations.data.api.FoundationsApi
import com.example.foundations.databinding.FragmentFoundationsBinding
import com.example.foundations.di.FoundationsFeatureComponent

class FoundationsFragment : BaseFragment<FoundationsViewModel>() {

    private lateinit var binding: FragmentFoundationsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoundationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)
    }

    override fun inject() {
        FeatureUtils.getFeature<FoundationsFeatureComponent>(this, FoundationsApi::class.java)
            .foundationsComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: FoundationsViewModel) {
        with(viewModel) {
        }
    }

    private fun showError(error: Throwable) {
        activity?.findViewById<View>(android.R.id.content)
            ?.showSnackbar(error.message ?: "Error")
    }
}