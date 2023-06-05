package com.example.foundation_info.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.load
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.foundation_info.R
import com.example.foundation_info.data.api.foundation.FoundationApi
import com.example.foundation_info.databinding.FragmentFoundationBinding
import com.example.foundation_info.di.FoundationFeatureComponent
import com.example.foundation_info.domain.entity.FoundationEntity

class FoundationFragment : BaseFragment<FoundationViewModel>() {

    private lateinit var binding: FragmentFoundationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoundationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribe(viewModel)

        getFoundation()

        binding.run {
            toolbar.tb.setNavigationOnClickListener {
                goBack()
            }
            toolbar.ivFavourite.setOnClickListener {

            }
            btnDonate.btnSubmit.setOnClickListener {

            }
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
            foundation.observe(viewLifecycleOwner) { foundation ->
                isFavouriteChanged(foundation)
                showViews(foundation)
                binding.btnDonate.btnSubmit.setOnClickListener {
                    onDonateClick(foundation.id, foundation.name)
                }
            }
            isFavourite.observe(viewLifecycleOwner) {
                val foundationEntity = foundation.value ?: return@observe
                changeFavouriteMode(it, foundationEntity)
            }
            error.observe(viewLifecycleOwner) {
                showError(it != null)
            }
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
    }

    private fun goBack() {
        viewModel.goBack()
    }

    private fun getFoundation() {
        arguments?.getLong(ARG_NAME)?.let {
            viewModel.getFoundation(it)
        }
    }

    private fun isFavouriteChanged(foundationEntity: FoundationEntity) {
        viewModel.isInFavourite(foundationEntity)
    }

    private fun changeFavouriteMode(isFavourite: Boolean, foundationEntity: FoundationEntity) {
        with(binding.toolbar.ivFavourite) {
            if (isFavourite) {
                setImageResource(R.drawable.ic_favourite_fill1)
                setOnClickListener {
                    viewModel.removeFromFavourite(foundationEntity)
                }
            } else {
                setImageResource(R.drawable.ic_favourite_fill0)
                setOnClickListener {
                    viewModel.addToFavourite(foundationEntity)
                }
            }
        }
    }

    private fun showLoading(flag: Boolean) {
        binding.loading.isVisible = flag
        binding.content.isVisible = !flag
    }

    private fun showError(flag: Boolean) {
        binding.lError.isVisible = flag
        binding.content.isVisible = !flag
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
                ivPreview.load(BASE_URL + image) {
                    crossfade(true)
                    crossfade(DURATION)
                    placeholder(com.example.theme.R.drawable.ic_photo)
                }
            }
        }
    }

    companion object {
        private const val ARG_NAME = "foundationId"
        private const val DURATION = 200
        private const val BASE_URL = "http://192.168.144.30:9999/image?name="
    }
}