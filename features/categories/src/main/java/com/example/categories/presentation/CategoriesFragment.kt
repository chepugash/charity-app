package com.example.categories.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.categories.data.api.CategoriesApi
import com.example.categories.databinding.FragmentCategoriesBinding
import com.example.categories.di.CategoriesFeatureComponent
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils

class CategoriesFragment : BaseFragment<CategoriesViewModel>() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)
    }

    override fun inject() {
        FeatureUtils.getFeature<CategoriesFeatureComponent>(this, CategoriesApi::class.java)
            .categoriesComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: CategoriesViewModel) {
    }
}