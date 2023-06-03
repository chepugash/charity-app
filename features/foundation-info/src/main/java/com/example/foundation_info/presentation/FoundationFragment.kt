package com.example.foundation_info.presentation

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.load
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
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

        arguments?.getLong(ARG_NAME)?.let {
            getFoundation(it)
        }

        binding.run {
            toolbar.tb.setNavigationOnClickListener {
                goBack()
            }
            toolbar.ivFavourite.setOnClickListener {

            }
            tvPhone.setOnClickListener {
                makeCall(tvPhone.text.toString())
            }
            tvWebsite.setOnClickListener {
                makeSearch(tvWebsite.text.toString())
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
                if (it == null) return@observe
                showError(it)
            }
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
    }

    private fun goBack() {
        viewModel.goBack()
    }

    private fun getFoundation(foundationId: Long) {
        viewModel.getFoundation(foundationId)
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
        with(binding) {
            loading.isVisible = flag
            content.isVisible = !flag
        }
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
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

    private fun makeCall(phone: String) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
    }

    private fun makeSearch(url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_WEB_SEARCH
            putExtra(SearchManager.QUERY, url)
        }
        startActivity(intent)
    }

    companion object {
        private const val ARG_NAME = "foundationId"
        private const val DURATION = 200
        private const val BASE_URL = "http://192.168.144.30:9999/image?name="
    }
}