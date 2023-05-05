package com.example.sign.presentation.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.sign.presentation.SignInFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SignInModule::class
    ]
)
@ScreenScope
interface SignInComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): SignInComponent
    }

    fun inject(fragment: SignInFragment)
}