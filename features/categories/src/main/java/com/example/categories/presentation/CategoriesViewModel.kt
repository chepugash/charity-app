package com.example.categories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.categories.CategoriesRouter
import com.example.categories.domain.entity.CategoryEntity
import com.example.categories.domain.usecase.GetCategoriesUseCase
import com.example.common.base.BaseViewModel
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val router: CategoriesRouter
) : BaseViewModel() {

    private val _categoryList = MutableLiveData<List<CategoryEntity>>()
    val categoryList: LiveData<List<CategoryEntity>>
        get() = _categoryList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getCategories() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _categoryList.value = getCategoriesUseCase.invoke()
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }
}