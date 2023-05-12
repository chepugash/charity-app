package com.example.sign.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.sign.data.SignApi
import com.example.sign.di.SignFeatureComponent
import com.example.sign_up.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<SignInViewModel>() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            btnSubmit.setOnClickListener {
                viewModel.onSubmitClick(itEmail.text.toString(), itPassword.toString())
            }
            tvSignIn.setOnClickListener {
                viewModel.onSignUpClick()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SignFeatureComponent>(this, SignApi::class.java)
            .signInComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: SignInViewModel) {
        with(viewModel) {
            loading.observe(viewLifecycleOwner) {
                binding.pbLoading.isVisible = it
            }
            userInfo.observe(viewLifecycleOwner) {
                if (it == null) return@observe
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
            }
        }
    }
}