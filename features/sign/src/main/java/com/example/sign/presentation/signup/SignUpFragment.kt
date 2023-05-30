package com.example.sign.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.sign.data.api.SignApi
import com.example.sign.di.SignFeatureComponent
import com.example.sign.domain.entity.ApiResult
import com.example.sign_up.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            btnSubmit.setOnClickListener {
                onSubmitClick(
                    email.itEmail.text.toString(),
                    password.itPassword.text.toString(),
                    repeatPassword.itRepeatPassword.text.toString()
                )
            }
            tvSignIn.setOnClickListener {
                onSignInClick()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SignFeatureComponent>(this, SignApi::class.java)
            .signUpComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: SignUpViewModel) {
        with(viewModel) {
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            apiResult.observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResult.Success -> {
                        viewModel.launchSignIn()
                    }

                    is ApiResult.Error -> {
                        showError(it.message)
                    }
                }
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showError(it)
            }
        }
    }

    private fun onSubmitClick(email: String, password: String, repeatPassword: String) {
        viewModel.onSubmitClick(email, password, repeatPassword)
    }

    private fun onSignInClick() {
        viewModel.onSignInClick()
    }

    private fun showLoading(flag: Boolean) {
        with(binding) {
            ivCreating.isVisible = !flag
            tvCreate.isVisible = !flag
            email.tfEmail.isVisible = !flag
            password.tfPassword.isVisible = !flag
            tvSignIn.isVisible = !flag
            btnSubmit.isVisible = !flag
            tvIsExist.isVisible = !flag
            repeatPassword.tfRepeatPassword.isVisible = !flag
            loading.isVisible = flag
        }
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
    }

    private fun showError(message: String) {
        binding.root.showSnackbar(message)
    }
}