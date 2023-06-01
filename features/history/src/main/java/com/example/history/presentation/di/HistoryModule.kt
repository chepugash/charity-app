package com.example.history.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.history.HistoryRouter
import com.example.history.presentation.HistoryViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class HistoryModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): HistoryViewModel {
        return ViewModelProvider(fragment, factory)[HistoryViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun provideHistoryViewModel(
        router: HistoryRouter
    ): ViewModel = HistoryViewModel(
        router
    )
}