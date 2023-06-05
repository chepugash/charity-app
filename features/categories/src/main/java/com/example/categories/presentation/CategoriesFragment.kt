package com.example.categories.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.categories.data.api.CategoriesApi
import com.example.categories.databinding.FragmentCategoriesBinding
import com.example.categories.di.CategoriesFeatureComponent
import com.example.categories.presentation.adapter.CategoryAdapter
import com.example.categories.presentation.adapter.SpaceItemDecorator
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showToast

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
                adapter.submitList(list)
                binding.rvCategories.run {
                    adapter = this@CategoriesFragment.adapter
                }
            }
            error.observe(viewLifecycleOwner) {
                showError(it != null)
            }
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
    }

    private fun showLoading(flag: Boolean) {
        binding.loading.isVisible = flag
        binding.rvCategories.isVisible = !flag
    }

    private fun showError(flag: Boolean) {
        binding.lError.isVisible = flag
        binding.rvCategories.isVisible = !flag
    }

    private fun getCategories() {
        viewModel.getCategories()
    }

    private fun addDecorator() {
        binding.rvCategories.addItemDecoration(itemDecoration)
    }

    private fun showError(error: Throwable) {
        binding.root.showToast(error.message ?: "Error")
    }
}