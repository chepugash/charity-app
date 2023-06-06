package com.example.categories.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.categories.data.api.CategoriesApi
import com.example.categories.databinding.FragmentCategoriesBinding
import com.example.categories.di.CategoriesFeatureComponent
import com.example.categories.presentation.adapter.CategoryAdapter
import com.example.categories.presentation.adapter.SpaceItemDecorator
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.hide
import com.example.common.utils.show

class CategoriesFragment : BaseFragment<CategoriesViewModel>() {

    private lateinit var binding: FragmentCategoriesBinding

    private val adapter: CategoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoryAdapter { viewModel.launchFoundations(it) }
    }

    private val itemDecoration by lazy(LazyThreadSafetyMode.NONE) {
        SpaceItemDecorator(this@CategoriesFragment, 16.0f)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDecorator()
        setupAdapter()

        subscribe(viewModel)
        getCategories()
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
        FeatureUtils.getFeature<CategoriesFeatureComponent>(this, CategoriesApi::class.java)
            .categoriesComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: CategoriesViewModel) {
        with(viewModel) {
            categoryList.observe(viewLifecycleOwner) { list ->
                if (list == null) return@observe
                binding.rvCategories.show()
                adapter.submitList(list)
            }
            error.observe(viewLifecycleOwner) { error ->
                if (error == null) return@observe
                showError()
            }
            loading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.loading.show()
        binding.rvCategories.hide()
    }

    private fun showError() {
        binding.lError.show()
        binding.rvCategories.hide()
        binding.loading.hide()

    }

    private fun getCategories() {
        viewModel.getCategories()
    }

    private fun setupAdapter() {
        binding.rvCategories.apply {
            adapter = this@CategoriesFragment.adapter
        }
    }

    private fun addDecorator() {
        binding.rvCategories.addItemDecoration(itemDecoration)
    }
}