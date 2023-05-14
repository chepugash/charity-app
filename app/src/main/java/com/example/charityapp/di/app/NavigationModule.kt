package com.example.charityapp.di.app

import com.example.charityapp.navigation.Navigator
import com.example.common.di.scope.ApplicationScope
import com.example.profile.ProfileRouter
import com.example.sign.SignRouter
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @ApplicationScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @ApplicationScope
    @Provides
    fun provideMainRouter(navigator: Navigator): SignRouter = navigator

    @ApplicationScope
    @Provides
    fun provideProfileRouter(navigator: Navigator): ProfileRouter = navigator
}