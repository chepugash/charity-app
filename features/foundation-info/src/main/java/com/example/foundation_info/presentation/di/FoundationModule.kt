package com.example.foundation_info.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.foundation_info.FoundationRouter
import com.example.foundation_info.domain.usecase.GetFoundationUseCase
import com.example.foundation_info.presentation.FoundationViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class FoundationModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): FoundationViewModel {
        return ViewModelProvider(fragment, factory)[FoundationViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(FoundationViewModel::class)
    fun provideCategoriesViewModel(
        getFoundationUseCase: GetFoundationUseCase,
        router: FoundationRouter
    ): ViewModel = FoundationViewModel(
        getFoundationUseCase,
        router
    )
}