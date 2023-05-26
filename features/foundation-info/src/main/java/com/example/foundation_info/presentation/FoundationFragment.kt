package com.example.foundation_info.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.foundation_info.data.api.FoundationApi
import com.example.foundation_info.databinding.FragmentFoundationBinding
import com.example.foundation_info.di.FoundationFeatureComponent
import com.example.foundation_info.domain.entity.FoundationEntity

class FoundationFragment : BaseFragment<FoundationViewModel>() {

    private lateinit var binding: FragmentFoundationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoundationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)
        arguments?.getInt("foundationId")?.let { viewModel.getFoundation(it) }

        binding.toolbar.tb.setNavigationOnClickListener {
            viewModel.goBack()
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<FoundationFeatureComponent>(this, FoundationApi::class.java)
            .foundationComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: FoundationViewModel) {
        with(viewModel) {
            foundation.observe(viewLifecycleOwner) {
                showViews(it)
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showError(it)
            }
        }
    }

    private fun showError(error: Throwable) {
        activity?.findViewById<View>(android.R.id.content)
            ?.showSnackbar(error.message ?: "Error")
    }

    private fun showViews(entity: FoundationEntity) {
        with(binding) {
            with(entity) {
                toolbar.tb.title = name
                tvAccount.text = account
                tvAddress.text = address
                tvInfo.text = description
                tvPhone.text = phone
                tvWebsite.text = website
                ivPreview.load("http://192.168.21.30:9999/image?name=${image}") {
                    crossfade(true)
                }
            }
        }
    }
}