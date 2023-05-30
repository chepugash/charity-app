package com.example.sign.presentation.nouser.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.sign.presentation.nouser.NoUserFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NoUserModule::class
    ]
)
@ScreenScope
interface NoUserComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): NoUserComponent
    }

    fun inject(fragment: NoUserFragment)
}