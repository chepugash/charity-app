package com.example.foundations.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.foundations.FoundationsRouter
import com.example.foundations.domain.usecase.GetFoundationsUseCase
import com.example.foundations.presentation.FoundationsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class FoundationsModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): FoundationsViewModel {
        return ViewModelProvider(fragment, factory)[FoundationsViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(FoundationsViewModel::class)
    fun provideFoundationsViewModel(
        getFoundationsUseCase: GetFoundationsUseCase,
        router: FoundationsRouter
    ): ViewModel = FoundationsViewModel(
        getFoundationsUseCase,
        router
    )
}