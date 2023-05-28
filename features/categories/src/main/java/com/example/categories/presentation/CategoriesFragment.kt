package com.example.categories.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.categories.data.api.CategoriesApi
import com.example.categories.databinding.FragmentCategoriesBinding
import com.example.categories.di.CategoriesFeatureComponent
import com.example.categories.presentation.adapter.CategoryAdapter
import com.example.categories.presentation.adapter.SpaceItemDecorator
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar

class CategoriesFragment : BaseFragment<CategoriesViewModel>() {

    private lateinit var binding: FragmentCategoriesBinding

    private val adapter: CategoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoryAdapter {
            viewModel.launchFoundations(it)
        }
    }

    private val itemDecoration by lazy(LazyThreadSafetyMode.NONE) {
        SpaceItemDecorator(
            this@CategoriesFragment,
            16.0f
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDecorator()
        subscribe(viewModel)
        getCategories()
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
                adapter.submitList(list)
                binding.rvCategories.run {
                    adapter = this@CategoriesFragment.adapter
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
            if (flag) {
                loading.isVisible = true
                rvCategories.isVisible = false
            } else {
                loading.isVisible = false
                rvCategories.isVisible = true
            }
        }
    }

    private fun getCategories() {
        viewModel.getCategories()
    }

    private fun addDecorator() {
        binding.rvCategories.addItemDecoration(itemDecoration)
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
    }
}