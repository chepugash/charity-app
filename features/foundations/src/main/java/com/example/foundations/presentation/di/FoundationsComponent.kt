package com.example.foundations.presentation.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.foundations.presentation.FoundationsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        FoundationsModule::class
    ]
)
@ScreenScope
interface FoundationsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): FoundationsComponent
    }

    fun inject(fragment: FoundationsFragment)
}