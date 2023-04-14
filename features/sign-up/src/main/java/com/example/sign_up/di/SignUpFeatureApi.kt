package com.example.sign_up.di

import com.example.common.data.network.NetworkApiCreator

interface SignUpFeatureApi {

    fun networkApiCreator(): NetworkApiCreator
}