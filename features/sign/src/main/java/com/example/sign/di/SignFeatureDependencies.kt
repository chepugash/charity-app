package com.example.sign.di

import com.example.common.data.network.NetworkApiCreator

interface SignFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator
}