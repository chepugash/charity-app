package com.example.categories.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categories.CategoriesRouter
import com.example.categories.domain.usecase.GetCategoriesUseCase
import com.example.categories.presentation.CategoriesViewModel
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class CategoriesModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): CategoriesViewModel {
        return ViewModelProvider(fragment, factory)[CategoriesViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun provideCategoriesViewModel(
        getCategoriesUseCase: GetCategoriesUseCase,
        router: CategoriesRouter
    ): ViewModel = CategoriesViewModel(
        getCategoriesUseCase,
        router
    )
}