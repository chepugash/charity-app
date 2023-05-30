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

    private lateinit var foundationEntity: FoundationEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoundationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribe(viewModel)
        getUser()

        arguments?.getInt(ARG_NAME)?.let {
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
                onDonateClick(tvAccount.text.toString())
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
            foundation.observe(viewLifecycleOwner) {
                foundationEntity = it
                isFavouriteChanged(foundationEntity)
                showViews(foundationEntity)
            }
            isFavourite.observe(viewLifecycleOwner) {
                changeFavouriteMode(it, foundationEntity)
            }
            user.observe(viewLifecycleOwner) {
                showFavourite(it != null)
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

    private fun getUser() {
        viewModel.getUser()
    }

    private fun goBack() {
        viewModel.goBack()
    }

    private fun onDonateClick(paymentInfo: String) {
        viewModel.onDonateClick(paymentInfo)
    }

    private fun getFoundation(foundationId: Int) {
        viewModel.getFoundation(foundationId)
    }

    private fun isFavouriteChanged(foundationEntity: FoundationEntity) {
        viewModel.isInFavourite(foundationEntity)
    }

    private fun showFavourite(flag: Boolean) {
        binding.toolbar.ivFavourite.isVisible = flag
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
            if (flag) {
                loading.isVisible = true
                content.isVisible = false
            } else {
                loading.isVisible = false
                content.isVisible = true
            }
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
                ivPreview.load("http://192.168.21.30:9999/image?name=${image}") {
                    crossfade(true)
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
    }
}