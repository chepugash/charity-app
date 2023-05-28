package com.example.favourite.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.favourite.FavouriteRouter
import com.example.favourite.presentation.FavouriteViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class FavouriteModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): FavouriteViewModel {
        return ViewModelProvider(fragment, factory)[FavouriteViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    fun provideFavouriteViewModel(
        router: FavouriteRouter
    ): ViewModel = FavouriteViewModel(
        router
    )
}