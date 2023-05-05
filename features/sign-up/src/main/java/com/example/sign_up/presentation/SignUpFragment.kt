package com.example.sign_up.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.sign_up.databinding.FragmentSignUpBinding
import com.example.sign_up.di.SignUpFeatureApi
import com.example.sign_up.di.SignUpFeatureComponent

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            btnSubmit.setOnClickListener {
                viewModel.onSubmitClick(
                    itEmail.text.toString(),
                    itPassword.toString(),
                    itRepeatPassword.toString()
                )
            }
            tvSignIn.setOnClickListener {
                viewModel.onSignInClick()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SignUpFeatureComponent>(this, SignUpFeatureApi::class.java)
            .signUpComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: SignUpViewModel) {
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