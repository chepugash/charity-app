package com.example.sign.presentation.sign_in

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
import com.example.sign.domain.entity.AuthResult
import com.example.sign_up.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<SignInViewModel>() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            btnSubmit.setOnClickListener {
                viewModel.onSubmitClick(itEmail.text.toString(), itPassword.text.toString())
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
            authResult.observe(viewLifecycleOwner) {
                when (it) {
                    is AuthResult.Success -> {
                        viewModel.launchSignUp()
                    }
                    is AuthResult.Error -> {
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

    private fun showError(error: Throwable) {
        activity?.findViewById<View>(android.R.id.content)
            ?.showSnackbar(error.message ?: "Error")
    }

    private fun showError(message: String) {
        activity?.findViewById<View>(android.R.id.content)
            ?.showSnackbar(message)
    }
}