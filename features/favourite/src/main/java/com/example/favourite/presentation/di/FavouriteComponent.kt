package com.example.favourite.presentation.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.favourite.presentation.FavouriteFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        FavouriteModule::class
    ]
)
@ScreenScope
interface FavouriteComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): FavouriteComponent
    }

    fun inject(fragment: FavouriteFragment)
}