package com.example.history.presentation.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.history.presentation.HistoryFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        HistoryModule::class
    ]
)
@ScreenScope
interface HistoryComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): HistoryComponent
    }

    fun inject(fragment: HistoryFragment)
}