package com.example.foundation_info.presentation.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.foundation_info.presentation.FoundationFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        FoundationModule::class
    ]
)
@ScreenScope
interface FoundationComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): FoundationComponent
    }

    fun inject(fragment: FoundationFragment)
}