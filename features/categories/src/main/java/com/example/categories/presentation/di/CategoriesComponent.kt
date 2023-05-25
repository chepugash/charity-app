package com.example.categories.presentation.di

import androidx.fragment.app.Fragment
import com.example.categories.presentation.CategoriesFragment
import com.example.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CategoriesModule::class
    ]
)
@ScreenScope
interface CategoriesComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): CategoriesComponent
    }

    fun inject(fragment: CategoriesFragment)
}