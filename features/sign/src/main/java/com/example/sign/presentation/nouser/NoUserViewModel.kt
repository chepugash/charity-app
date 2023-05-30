package com.example.sign.presentation.nouser

import com.example.common.base.BaseViewModel
import com.example.sign.SignRouter

class NoUserViewModel(
    private val router: SignRouter
) : BaseViewModel() {

    fun onSignInClick() {
        router.launchSignIn()
    }

    fun onSignUpClick() {
        router.launchSignUp()
    }
}