package com.example.categories.presentation

import com.example.categories.CategoriesRouter
import com.example.categories.domain.usecase.GetCategoriesUseCase
import com.example.common.base.BaseViewModel

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val router: CategoriesRouter
) : BaseViewModel() {
}