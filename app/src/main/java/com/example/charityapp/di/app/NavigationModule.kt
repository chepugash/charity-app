package com.example.charityapp.di.app

import com.example.charityapp.navigation.Navigator
import com.example.common.di.scope.ApplicationScope
import com.example.sign.SignUpRouter
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @ApplicationScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @ApplicationScope
    @Provides
    fun provideMainRouter(navigator: Navigator): SignUpRouter = navigator
}