package com.example.sign_up

import android.content.Context

interface SignUpRouter {

    fun launchSignIn()

    fun openMain(context: Context)
}