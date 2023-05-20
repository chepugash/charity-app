package com.example.profile.presentation.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.profile.presentation.dialog.NameDialogFragment
import com.example.profile.presentation.ProfileFragment
import com.example.profile.presentation.dialog.SignOutDialogFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ProfileModule::class
    ]
)
@ScreenScope
interface ProfileComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: NameDialogFragment)

    fun inject(fragment: SignOutDialogFragment)
}