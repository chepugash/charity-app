package com.example.foundation_info.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.foundation_info.data.api.FoundationApi
import com.example.foundation_info.databinding.FragmentFoundationBinding
import com.example.foundation_info.di.FoundationFeatureComponent

class FoundationFragment : BaseFragment<FoundationViewModel>() {

    private lateinit var binding: FragmentFoundationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoundationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)
    }

    override fun inject() {
        FeatureUtils.getFeature<FoundationFeatureComponent>(this, FoundationApi::class.java)
            .foundationComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: FoundationViewModel) {
        with(viewModel) {

        }
    }
}