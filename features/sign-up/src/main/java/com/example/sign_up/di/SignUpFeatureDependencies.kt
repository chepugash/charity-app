package com.example.sign_up.di

import com.example.common.data.network.NetworkApiCreator

interface SignUpFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator
}