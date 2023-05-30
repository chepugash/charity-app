package com.example.sign.presentation.nouser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.sign.data.api.SignApi
import com.example.sign.di.SignFeatureComponent
import com.example.sign_up.databinding.FragmentNoUserBinding

class NoUserFragment : BaseFragment<NoUserViewModel>() {

    private lateinit var binding: FragmentNoUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            tvSignIn.setOnClickListener {
                onSignInClick()
            }
            tvSignUp.setOnClickListener {
                onSignUpClick()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SignFeatureComponent>(this, SignApi::class.java)
            .noUserComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: NoUserViewModel) {
    }

    private fun onSignUpClick() {
        viewModel.onSignUpClick()
    }

    private fun onSignInClick() {
        viewModel.onSignInClick()
    }
}