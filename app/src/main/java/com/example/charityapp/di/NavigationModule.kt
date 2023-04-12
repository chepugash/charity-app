package com.example.charityapp.di

import com.example.charityapp.navigation.Navigator
import com.example.sign_up.presentation.SignUpRouter
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @AppScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @AppScope
    @Provides
    fun provideSignUpRouter(navigator: Navigator): SignUpRouter = navigator
}