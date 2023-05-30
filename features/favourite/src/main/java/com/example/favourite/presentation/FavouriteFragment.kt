package com.example.favourite.presentation

import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.di.FavouriteFeatureComponent

class FavouriteFragment : BaseFragment<FavouriteViewModel>() {
    override fun inject() {
        FeatureUtils.getFeature<FavouriteFeatureComponent>(this, FirebaseApi::class.java)
            .favouriteComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: FavouriteViewModel) {

    }
}