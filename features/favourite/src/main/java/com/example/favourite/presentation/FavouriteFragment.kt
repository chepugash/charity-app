package com.example.favourite.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.favourite.data.favourite.FavouriteApi
import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.databinding.FragmentFavouriteBinding
import com.example.favourite.di.FavouriteFeatureComponent
import com.example.favourite.presentation.adapter.FoundationAdapter
import com.example.favourite.presentation.adapter.SpaceItemDecorator

class FavouriteFragment : BaseFragment<FavouriteViewModel>() {

    private lateinit var binding: FragmentFavouriteBinding

    private val adapter: FoundationAdapter by lazy(LazyThreadSafetyMode.NONE) {
        FoundationAdapter {
            viewModel.launchFoundationInfo(it)
        }
    }

    private val itemDecoration by lazy(LazyThreadSafetyMode.NONE) {
        SpaceItemDecorator(
            this@FavouriteFragment,
            16.0f
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDecorator()
        subscribe(viewModel)

        getFavourite()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun inject() {
        FeatureUtils.getFeature<FavouriteFeatureComponent>(this, FavouriteApi::class.java)
            .favouriteComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: FavouriteViewModel) {
        with(viewModel) {
            foundationList.observe(viewLifecycleOwner) { list ->
                if (list == null || list.isEmpty()) {
                    showWhenEmptyList(true)
                } else {
                    showWhenEmptyList(false)
                    adapter.submitList(list)
                    binding.rvFoundations.run {
                        adapter = this@FavouriteFragment.adapter
                    }
                }
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

    private fun showLoading(flag: Boolean) {
        with(binding) {
            loading.isVisible = flag
            lEmpty.layoutEmpty.isVisible = !flag
            rvFoundations.isVisible = !flag
        }
    }

    private fun showWhenEmptyList(flag: Boolean) {
        with(binding) {
            loading.isVisible = !flag
            lEmpty.layoutEmpty.isVisible = flag
            rvFoundations.isVisible = !flag
        }
    }

    private fun getFavourite() {
        viewModel.getFavourite()
    }

    private fun addDecorator() {
        binding.rvFoundations.addItemDecoration(itemDecoration)
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
    }
}