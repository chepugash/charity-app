package com.example.sign.presentation.sign_up.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.sign.presentation.sign_up.SignUpFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SignUpModule::class
    ]
)
@ScreenScope
interface SignUpComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): SignUpComponent
    }

    fun inject(fragment: SignUpFragment)
}