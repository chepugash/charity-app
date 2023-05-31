package com.example.charityapp.di.app

import com.example.categories.CategoriesRouter
import com.example.charityapp.navigation.Navigator
import com.example.common.di.scope.ApplicationScope
import com.example.favourite.FavouriteRouter
import com.example.foundation_info.FoundationRouter
import com.example.foundations.FoundationsRouter
import com.example.payment.PaymentRouter
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

    @ApplicationScope
    @Provides
    fun provideCategoriesRouter(navigator: Navigator): CategoriesRouter = navigator

    @ApplicationScope
    @Provides
    fun provideFoundationsRouter(navigator: Navigator): FoundationsRouter = navigator

    @ApplicationScope
    @Provides
    fun provideFoundationRouter(navigator: Navigator): FoundationRouter = navigator

    @ApplicationScope
    @Provides
    fun providePaymentRouter(navigator: Navigator): PaymentRouter = navigator

    @ApplicationScope
    @Provides
    fun provideFavouriteRouter(navigator: Navigator): FavouriteRouter = navigator
}