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
import com.example.foundations.presentation.adapter.FoundationAdapter
import com.example.foundations.presentation.adapter.SpaceItemDecorator

class FoundationsFragment : BaseFragment<FoundationsViewModel>() {

    private lateinit var binding: FragmentFoundationsBinding

    private val adapter: FoundationAdapter by lazy(LazyThreadSafetyMode.NONE) {
        FoundationAdapter {
            viewModel.launchFoundation(it)
        }
    }

    private val itemDecoration by lazy(LazyThreadSafetyMode.NONE) {
        SpaceItemDecorator(
            this@FoundationsFragment,
            16.0f
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoundationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFoundations.addItemDecoration(itemDecoration)
        subscribe(viewModel)
        arguments?.getInt("categoryId")?.let { viewModel.getFoundations(it) }

        binding.toolbar.setNavigationOnClickListener {
            viewModel.goBack()
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<FoundationsFeatureComponent>(this, FoundationsApi::class.java)
            .foundationsComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: FoundationsViewModel) {
        with(viewModel) {
            foundationList.observe(viewLifecycleOwner) { list ->
                if (list == null) return@observe
                adapter.submitList(list)
                binding.rvFoundations.run {
                    adapter = this@FoundationsFragment.adapter
                }
            }
            category.observe(viewLifecycleOwner) {
                binding.toolbar.title = it
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
}